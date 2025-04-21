package com.example.letgozhenshan.Stage5;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;

import com.example.letgozhenshan.MainMap;
import com.example.letgozhenshan.R;
import com.google.android.material.progressindicator.CircularProgressIndicator;

public class WalkingGame extends AppCompatActivity implements SensorEventListener {


    private SensorManager sensorManager;
    private Sensor stepSensor;
    public static final int REQUEST_CODE = 1001;
    private int initialStepCount = -1; // 初始步數
    private int targetSteps = 500; // 目標步數
    private int currentStepCount;
    private TextView mStepTextView;
    private  boolean isEnd = false;
    private CircularProgressIndicator mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_walking_game);
        mProgressBar = findViewById(R.id.circularProgress);
        mProgressBar.setProgress(0, true);
        mStepTextView = findViewById(R.id.textView4);
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // 什麼都不做，直接忽略返回鍵
            }
        });
        // 初始化感測器
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            if (stepSensor == null) {
                Toast.makeText(this, "無法使用步數感測器", Toast.LENGTH_LONG).show();
            }
        }


            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED) {
            // 權限尚未授予，請求權限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, REQUEST_CODE);
        }
            //Test
//        Toast.makeText(this, "恭喜！您已完成500步！(⁎⁍̴̛ᴗ⁍̴̛⁎)", Toast.LENGTH_LONG).show();
//        MainMap.Point++;
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("遊戲結束");
//        builder.setMessage("你贏了！按下繼續前往下一關" );
//
//        builder.setPositiveButton("繼續", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                MainMap.Pass+=1;
//                MainMap.PassList.set(4, true);
//                finish();
//            }
//        });
//        AlertDialog dialog = builder.create();
//        dialog.setCancelable(false);  // 禁止按返回鍵關閉
//        dialog.show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (stepSensor != null) {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (stepSensor != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(isEnd) return;
        if (initialStepCount == -1) {
            initialStepCount = (int) event.values[0];
        }
        isEnd = true;
        currentStepCount = (int) event.values[0] - initialStepCount;
        mStepTextView.setText("" +currentStepCount);
        int percent = (int)(((float) currentStepCount / targetSteps) * 100);

        mProgressBar.setProgress(percent, true);

        if (currentStepCount >= targetSteps) {
            Toast.makeText(this, "恭喜！您已完成500步！(⁎⁍̴̛ᴗ⁍̴̛⁎)", Toast.LENGTH_LONG).show();
            MainMap.Point++;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("遊戲結束");
            builder.setMessage("你贏了！按下繼續前往下一關" );

            builder.setPositiveButton("繼續", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainMap.Pass+=1;
                    MainMap.PassList.set(4, true);
                    finish();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.setCancelable(false);  // 禁止按返回鍵關閉
            dialog.show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // 可以不用處理
    }
}