package com.example.letgozhenshan.Stage5;

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
import com.example.letgozhenshan.Stage2.MemoryGame;
import com.example.letgozhenshan.Stage2.MemoryGameDepiction;

import java.util.ArrayList;
import java.util.List;

public class WalkingGmaeDepiction extends AppCompatActivity {
    ImageView mImageView;
    TextView mTitleText , mContentText;
    Button mNextBtn , mBackBtn;
    IntroductionSystem mIntroductionSystem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_walking_gmae_depiction);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        List<IntroductionData> introList = new ArrayList<>();
        introList.add(new IntroductionData(null, "望龍埤", "數百年前因山洪暴發、土石崩落，自然形成一座湖泊。因山中湖泊的水位完全仰賴雨量的多寡，而水量更與地方居民息息相關，將湖泊取名「望龍」，祈求雨水豐足。\n"));
        introList.add(new IntroductionData(null, "步可思議", "遊戲說明:\n" +
                "遊客可沿環湖步道欣賞湖光水色、登上飛龍步道至景觀平台。\n" +
                "\n" +
                "步行500步即過關。\n"));

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

                Intent intent = new Intent(WalkingGmaeDepiction.this, WalkingGame.class);
                startActivity(intent);
                finish();
            }
        });
    }
}