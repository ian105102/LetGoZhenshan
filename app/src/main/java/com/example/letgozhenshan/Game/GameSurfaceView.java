package com.example.letgozhenshan.Game;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.letgozhenshan.Game.Objcet.GameObject;


public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback  {

    private GameThread gameThread; // 用來更新與繪圖
    private IGameThread gameLogic; // 遊戲邏輯實作
    private IHandleTouch handleTouch;

    public GameSurfaceView(Context context, IGameThread gameLogic , IHandleTouch handleTouch) {
        super(context);
        this.gameLogic = gameLogic;
        this.handleTouch = handleTouch;
        getHolder().addCallback(this);
        setFocusable(true);
    }
    public void pauseGame() {
        if (gameThread != null) {
            gameThread.setRunning(false);
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameThread = null;
        }
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // 當 SurfaceView 被創建時，開始 GameThread
        gameThread = new GameThread(holder, gameLogic);
        gameThread.setRunning(true);
        gameThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        // 停止 GameThread
        gameThread.setRunning(false);
        while (retry) {
            try {
                gameThread.join(); // 等待線程結束
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public Point getScreenSize() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        return new Point(screenWidth, screenHeight);
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 使用者觸碰螢幕
                handleTouchDown(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                // 使用者在螢幕上移動
                handleTouchMove(x, y);
                break;
            case MotionEvent.ACTION_UP:
                // 使用者抬起手指
                handleTouchUp(x, y);
                break;
            case MotionEvent.ACTION_CANCEL:
                // 觸控事件被取消
                break;
        }
        return true; // 返回 true 表示我們處理了該事件
    }

    // 處理觸控按下事件

    private void handleTouchDown(float x, float y) {
        // 處理觸控按下時的邏輯
        System.out.println("Touch Down at: " + x + ", " + y);
        this.handleTouch.handleTouchDown(x,y);
    }

    // 處理觸控移動事件
    private void handleTouchMove(float x, float y) {
        // 處理觸控移動時的邏輯
        System.out.println("Touch Move at: " + x + ", " + y);
        this.handleTouch.handleTouchMove(x,y);
    }

    // 處理觸控抬起事件
    private void handleTouchUp(float x, float y) {
        // 處理觸控抬起時的邏輯
        System.out.println("Touch Up at: " + x + ", " + y);
        this.handleTouch.handleTouchUp(x,y);
    }
}
