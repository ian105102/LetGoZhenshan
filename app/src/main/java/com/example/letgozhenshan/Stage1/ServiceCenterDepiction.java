package com.example.letgozhenshan.Stage1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.letgozhenshan.MainMap;
import com.example.letgozhenshan.R;

import java.util.ArrayList;
import java.util.List;

public class ServiceCenterDepiction extends AppCompatActivity {
    ImageView mImageView;
    TextView mTitleText , mContentText;
    Button mNextBtn , mBackBtn;
    IntroductionSystem mIntroductionSystem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_service_depiction);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<IntroductionData> introList = new ArrayList<>();
        introList.add(new IntroductionData(null, "枕頭山休區服務中心", "休區服務中心，一個開放的場域，服務中心可以帶你了解【枕山】，介紹這邊的食、住、育、樂、購好去處，建議行程、主題旅遊、環境教育、體驗活動、步道健行、玩水消暑通通都有。"));
        introList.add(new IntroductionData(R.drawable.d0, "枕頭山休閒農業區", "枕頭山休閒農業區位於宜蘭員山鄉，是蘭陽十八勝景之一。\n" +
                "紅心芭樂、蓮霧、李子、金棗、水梨、柑橘等農業發達，特別是金棗90%的年產量來自宜蘭。\n" +
                "同時也是歌仔戲的發源地，結頭份社區成立歌仔戲班傳承文化。"));
        introList.add(new IntroductionData(R.drawable.d1, "阿蘭城天然湧泉", "阿蘭城路旁的天然露天湧泉游泳池，屬於地下湧泉，水溫為恆溫22度左右，水質乾淨，四季提供游泳玩樂嬉戲。\n" +
                "\n" +
                "望龍埤湖光山色優美，為知名偶像劇「下一站幸福」拍攝地點之一，設有環湖步道與飛龍步道。"));
        introList.add(new IntroductionData(null, "枕山知多少?", "遊戲說明:\n" +
                "\n" +
                "了解枕頭山休區的相關知識後，在APP中會有5道選擇題。\n" +
                "\n" +
                "全部答對才能過關。"));

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

                Intent intent = new Intent(ServiceCenterDepiction.this, ServiceGame.class);
                startActivity(intent);
                finish();
            }
        });

    }




}