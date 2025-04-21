package com.example.letgozhenshan.Summary;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.letgozhenshan.MainMap;
import com.example.letgozhenshan.R;


public class Summary extends AppCompatActivity  {

    ImageView mWheel;


    private int[] five_angles = { 0, 60, 120,180, 240,300 };
    private int[] three_angles = { 0, 120, 240 };
    private int startDegree = 0;
    private int result=0;
    private static final long ONE_WHEEL_TIME = 1000;
    private Button mButton;
    private String[] five_lotteryStr = {  "50元折價券", "25元折價券","10元折價券",
            "5元折價券","手工壓花書籤", "手工冰棒" };
    private String[] three_lotteryStr = { "10元折價券", "5元折價券", "手工冰棒",  };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mWheel = findViewById(R.id.imageView2);
        mButton = findViewById(R.id.button3);
        if(MainMap.Point>4){
            mWheel.setImageResource(R.drawable.wheel5);
        }else{
            mWheel.setImageResource(R.drawable.wheel3);
        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mButton.setEnabled(false);
                int targetAngle =0;
                if(MainMap.Point>4){
                    result = (int)(Math.random() * five_angles.length);

                    targetAngle = five_angles[result]+ (int)(Math.random()*360/five_angles.length);
                }else{
                    result = (int)(Math.random() * three_angles.length);
                    targetAngle = three_angles[result] + (int)(Math.random()*360/three_angles.length);
                }

                rotateWheelToTarget(targetAngle);
            }
        });
    }
    private void rotateWheelToTarget(int targetAngle) {
        int lap = (int)(Math.random()*5)+5; // 決定要轉幾圈
        int increaseDegree = lap * 360 + targetAngle;

        RotateAnimation rotateAnimation = new RotateAnimation(
                startDegree, startDegree + increaseDegree -(startDegree%360),
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        startDegree +=  increaseDegree -(startDegree%360); // 記得更新起始角度

        long time = lap * ONE_WHEEL_TIME + 500; // 可自訂時間
        rotateAnimation.setDuration(time);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(this, android.R.anim.accelerate_decelerate_interpolator);
        rotateAnimation.setAnimationListener(al);
        mWheel.startAnimation(rotateAnimation);
    }


    private Animation.AnimationListener al = new Animation.AnimationListener() {

        @Override
        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(MainMap.Point>4){
                Toast.makeText(getBaseContext() , "恭喜獲得"+five_lotteryStr[result] , Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getBaseContext() , "恭喜獲得"+three_lotteryStr[result] , Toast.LENGTH_SHORT).show();
            }
        }
    };
}