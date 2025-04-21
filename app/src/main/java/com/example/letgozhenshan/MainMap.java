package com.example.letgozhenshan;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.letgozhenshan.Stage1.ServiceCenterDepiction;
import com.example.letgozhenshan.Stage2.MemoryGameDepiction;
import com.example.letgozhenshan.Stage3.InsectGameDepiction;
import com.example.letgozhenshan.Stage4.MuquSucculentjoyFarmDepiction;
import com.example.letgozhenshan.Stage5.WalkingGmaeDepiction;
import com.example.letgozhenshan.Summary.Summary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainMap extends AppCompatActivity {
    Button mStage1Btn , mStage2Btn , mStage3Btn, mStage4Btn, mStage5Btn , Endbutton , qButton;
    TextView mTextView;
    public static int Point =0;
    public static int Pass =0;
    public static List<Boolean> PassList = new ArrayList<Boolean>(Arrays.asList(false, false, false, false, false));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_map);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

            }
        });
        mStage1Btn = findViewById(R.id.button1);
        mStage2Btn = findViewById(R.id.button2);
        mStage3Btn = findViewById(R.id.button3);
        mStage4Btn = findViewById(R.id.button4);
        mStage5Btn = findViewById(R.id.button5);
        qButton = findViewById(R.id.button13);

        qButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainMap.this);
                builder.setTitle("玩法");
                builder.setMessage("每過一個關卡，過關數+1\n" +
                        "五關中過四關以上，即可抽獎\n" +
                        "\n" +
                        "若有關卡沒過關，不能重玩!!\n" +
                        "\n" +
                        "遊戲結束請按 \"結算獎勵\"\n" +
                        "\n" +
                        "點選地圖   即可進入關卡\n" +
                        "\n" );

                builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setCancelable(false);  // 禁止按返回鍵關閉
                dialog.show();
            }
        });



        Endbutton = findViewById(R.id.Endbutton);
        mTextView = findViewById(R.id.textView3);


        mStage1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMap.this, ServiceCenterDepiction.class);
                startActivity(intent);
            }
        });

        mStage2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainMap.this, MemoryGameDepiction.class);
                startActivity(intent);

            }
        });
        mStage3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMap.this, InsectGameDepiction.class);
                startActivity(intent);
            }
        });
        mStage4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMap.this, MuquSucculentjoyFarmDepiction.class);
                startActivity(intent);
            }
        });
        mStage5Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMap.this, WalkingGmaeDepiction.class);
                startActivity(intent);
            }
        });

        Endbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Pass>=5){
                    Intent intent = new Intent(MainMap.this, Summary.class);
                    startActivity(intent);
                }else if(Pass>=4){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainMap.this);
                    builder.setTitle("小提示");
                    builder.setMessage("五關裡面要過四關才能抽獎喔！\n" +
                            "請問你確定要結算獎勵嗎？" );

                    builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainMap.this, Summary.class);
                            startActivity(intent);
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.setCancelable(false);  // 禁止按返回鍵關閉
                    dialog.show();
                }else{
                    Toast.makeText(getBaseContext() , "五關裡面要過四關才能抽獎喔?再加油!!" , Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    protected void onResume() {

        mTextView.setText("關卡:"+MainMap.Point+"/5");
        for(int i = 0 ; i <MainMap.PassList.size()  ; i++){
            Button setButton = null;
            switch (i){
                case 0:
                    setButton = mStage1Btn;
                    break;
                case 1:
                    setButton = mStage2Btn;
                    break;
                case 2:
                    setButton = mStage3Btn;
                    break;
                case 3:
                    setButton = mStage4Btn;
                    break;
                case 4:
                    setButton = mStage5Btn;
                    break;
            }
            if (setButton != null) {
                // 根據 PassList 的值來啟用或禁用 Button
                setButton.setEnabled(!MainMap.PassList.get(i));
            }
        }
        super.onResume();
    }
}