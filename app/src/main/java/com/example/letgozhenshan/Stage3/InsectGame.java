package com.example.letgozhenshan.Stage3;

import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.example.letgozhenshan.Game.GameSurfaceView;
import com.example.letgozhenshan.Game.IGameThread;
import com.example.letgozhenshan.Game.IHandleTouch;
import com.example.letgozhenshan.Game.Objcet.GameObject;
import com.example.letgozhenshan.Game.Objcet.Insect;
import com.example.letgozhenshan.Game.Objcet.Plant;
import com.example.letgozhenshan.Game.Objcet.GameTimer;
import com.example.letgozhenshan.MainMap;
import com.example.letgozhenshan.R;

import java.util.ArrayList;
import java.util.Random;

public class InsectGame extends AppCompatActivity implements IGameThread , IHandleTouch {

    private GameSurfaceView mGameSurfaceView;

    private Plant plant;
    private Plant plant1;
    private Plant plant2;
    private Plant plant3;
    private GameTimer timer;

    private int Score = 0;
    public static int HEIGHT;
    public static int WIGHT;

    private boolean OnTouch = false;
    private float x, y;
    private ArrayList<Plant> Plants = new ArrayList<Plant>();
    private ArrayList<Insect> Insects = new ArrayList<Insect>();
    private ArrayList<Insect> bee = new ArrayList<Insect>();
    private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

            }
        });
        timer = new GameTimer(30);
        timer.setOnTick(() -> {
            Pair<Float, Float> speed_ = RandomSpeed(15);
            for(int i = 0 ; i< 5  ; i ++){
                if(Insects.size()>30) return;
                speed_ = RandomSpeed(new  Random().nextInt(10 + 1)+5);
                Insect insect = new Insect(this ,WIGHT , HEIGHT ,R.drawable.bug);
                insect.setPosition(WIGHT/2,HEIGHT/2);
                insect.setSpeed(speed_.first,speed_.second);
                gameObjects.add(insect);
                Insects.add(insect);
            }
            for(int i = 0 ; i< 2  ; i ++){
                if(bee.size()>30) return;
                speed_ = RandomSpeed(new  Random().nextInt(10 + 1)+5);
                Insect insect = new Insect(this ,WIGHT , HEIGHT ,R.drawable.bee);
                insect.setPosition(WIGHT/2,HEIGHT/2);
                insect.setSpeed(speed_.first,speed_.second);
                gameObjects.add(insect);
                bee.add(insect);
            }

        });
        timer.setOnFinish(() -> {
            runOnUiThread(() -> {
                mGameSurfaceView.pauseGame(); // 暫停 GameThread

                Log.d("Timer", "時間到！");
                boolean isWin = Score >= 20;
                AlertDialog.Builder builder = new AlertDialog.Builder(InsectGame.this);
                builder.setTitle("遊戲結束");
                builder.setMessage(isWin ? "你贏了！按下繼續前往下一關" : "你輸了...按下繼續前往下一關！");
                if(isWin){
                    MainMap.Point++;
                }
                builder.setPositiveButton("繼續", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainMap.Pass+=1;
                        MainMap.PassList.set(2, true);
                        finish();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.setCancelable(false);  // 禁止按返回鍵關閉
                dialog.show();
            });

        });
        timer.start();
        mGameSurfaceView = new GameSurfaceView(this, this , this);
        setContentView(mGameSurfaceView);
        Point screenSize = mGameSurfaceView.getScreenSize();
        WIGHT = screenSize.x;
        HEIGHT = screenSize.y;


        plant = new Plant(this ,WIGHT , HEIGHT , R.drawable.plant2 , 571,350);
        plant.setPosition(50,200);
        plant.setScale(2f,2f);
        gameObjects.add(plant);
        Plants.add(plant);

        plant1 = new Plant(this ,WIGHT , HEIGHT , R.drawable.plant3 ,380 , 461);
        plant1.setPosition(WIGHT-70,100);

        plant1.setScale(2.5f,2.5f);
        gameObjects.add(plant1);
        Plants.add(plant1);

        plant2 = new Plant(this ,WIGHT , HEIGHT , R.drawable.plant1 ,421,495 );
        plant2.setScale(1.8f,1.8f);

        plant2.setPosition(150,HEIGHT-200);
        gameObjects.add(plant2);
        Plants.add(plant2);

        plant3 = new Plant(this ,WIGHT , HEIGHT , R.drawable.plant4 , 302 , 211);
        plant3.setPosition(WIGHT-190,HEIGHT-200);

        plant3.setScale(1.8f,1.8f);
        gameObjects.add(plant3);

        Plants.add(plant3);

        Pair<Float, Float> speed = RandomSpeed(15);


        for(int i = 0 ; i< 5  ; i ++){
            speed = RandomSpeed(new  Random().nextInt(10 + 1)+5);
            Insect insect = new Insect(this ,WIGHT , HEIGHT ,R.drawable.bug);
            insect.setPosition(WIGHT/2,HEIGHT/2);
            insect.setSpeed(speed.first,speed.second);
            gameObjects.add(insect);
            Insects.add(insect);
        }
        for(int i = 0 ; i< 2  ; i ++){
            speed = RandomSpeed(new  Random().nextInt(10 + 1)+5);
            Insect insect = new Insect(this ,WIGHT , HEIGHT ,R.drawable.bee);
            insect.setPosition(WIGHT/2,HEIGHT/2);
            insect.setSpeed(speed.first,speed.second);
            gameObjects.add(insect);
            bee.add(insect);
        }


    }
    @Override
    public void update() {
        timer.update();

        for (GameObject item : gameObjects) {
            item.update();
        }
        if(OnTouch){
            for (Plant plant:Plants) {
                if(plant.Hitbox.checkPointCollision(x,y)){
                    for (int i = Insects.size() - 1; i >= 0; i--) {
                        Insect insect = Insects.get(i);
                        if (insect.Hitbox.checkCollision(plant.Hitbox)) {
                            if (!insect.getActive()) continue;
                            insect.setActive(false);
                            gameObjects.remove(insect);
                            Insects.remove(i);
                            Score++;
                        }
                    }

                    for (int i = bee.size() - 1; i >= 0; i--) {
                        Insect insect = bee.get(i);
                        if (insect.Hitbox.checkCollision(plant.Hitbox)) {
                            if (!insect.getActive()) continue;
                            insect.setActive(false);
                            gameObjects.remove(insect);
                            bee.remove(i);
                            Score--;
                        }
                    }
                }
            }
            OnTouch = false;
        }


    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#8DA250"));


        for (GameObject item : gameObjects) {
            item.draw(canvas);
        }
        drawText(canvas , "分數:" + Score , WIGHT/2 , 50 );
        drawText(canvas , "時間:" + timer.getRemainingSeconds() , WIGHT/2 , 100 );
    }

    @Override
    public void handleTouchDown(float x, float y) {
        OnTouch = true;
        this.x = x ;
        this.y = y;
    }

    public void drawText(Canvas canvas, String s ,int  x,int y) {
        // 設定 Paint 物件來控制文字樣式
        Paint paint = new Paint();
        paint.setColor(Color.BLACK); // 設定文字顏色
        paint.setTextSize(50); // 設定文字大小
        paint.setStyle(Paint.Style.FILL); // 填充樣式

        // 顯示分數
        canvas.drawText(s, x, y, paint); // 在畫布上的 (50, 50) 坐標顯示分數
    }


    public Pair<Float, Float> RandomSpeed(float speedMagnitude) {
        Random random = new Random();
        double angleDegrees = random.nextDouble() * 360.0;  // 0 ~ 360 度
        double angleRadians = Math.toRadians(angleDegrees);

        float dx = (float)(Math.cos(angleRadians) * speedMagnitude);
        float dy = (float)(Math.sin(angleRadians) * speedMagnitude);

        return new Pair<>(dx, dy);  // 返回一個 Pair
    }
    @Override
    public void handleTouchMove(float x, float y) {

    }

    @Override
    public void handleTouchUp(float x, float y) {

    }
}