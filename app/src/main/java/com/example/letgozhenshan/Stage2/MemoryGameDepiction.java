package com.example.letgozhenshan.Stage2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.letgozhenshan.Introduction.IntroductionData;
import com.example.letgozhenshan.Introduction.IntroductionSystem;
import com.example.letgozhenshan.R;
import com.example.letgozhenshan.Stage1.ServiceCenterDepiction;
import com.example.letgozhenshan.Stage1.ServiceGame;

import java.util.ArrayList;
import java.util.List;

public class MemoryGameDepiction extends AppCompatActivity {
    ImageView mImageView;
    TextView mTitleText , mContentText;
    Button mNextBtn , mBackBtn;
    IntroductionSystem mIntroductionSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_memory_game_depiction);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<IntroductionData> introList = new ArrayList<>();
        introList.add(new IntroductionData(null, "金雙甡花卉農場", "園區中盆栽盎然，各式盆景、苗木應有盡有，還有攀爬、垂吊著許多花草，多樣性的亞熱帶果樹及花卉，宛如一座植物大觀園。"));
        introList.add(new IntroductionData(null, "文心蘭黃金隧道", "每年約5月底至6月初、近500盆金黃色的文心蘭，打造出長達約100公尺金黃色花朵交錯編織成夢幻又浪漫的金黃色隧道，陽光下的斜影灑落花朵形成一條浪漫步道宛如仙境。\n"));
        introList.add(new IntroductionData(null, "花現你", "遊戲說明:\n" +
                "請注意畫面的圖卡，十秒後遊戲開始，請翻出一樣的圖卡，相同圖卡會消除。\n" +
                "\n" +
                "全部消除完，即可過關。\n"));

        mImageView = findViewById(R.id.imageView);
        mTitleText = findViewById(R.id.textView5);
        mContentText = findViewById(R.id.textView11);
        mNextBtn = findViewById(R.id.button12);
        mBackBtn = findViewById(R.id.button11);



        mIntroductionSystem = new IntroductionSystem(
                mImageView,
                mTitleText,
                mContentText,
                mBackBtn,
                mNextBtn,
                introList
        );
        mIntroductionSystem.setOnEndReachedCallback(new IntroductionSystem.OnEndReachedCallback() {
            @Override
            public void onEndReached() {

                Intent intent = new Intent(MemoryGameDepiction.this, MemoryGame.class);
                startActivity(intent);
                finish();
            }
        });
    }
}