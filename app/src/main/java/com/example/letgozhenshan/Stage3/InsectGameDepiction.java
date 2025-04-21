package com.example.letgozhenshan.Stage3;

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

public class InsectGameDepiction extends AppCompatActivity {
    ImageView mImageView;
    TextView mTitleText , mContentText;
    Button mNextBtn , mBackBtn;
    IntroductionSystem mIntroductionSystem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insect_game_depiction);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        List<IntroductionData> introList = new ArrayList<>();
        introList.add(new IntroductionData(null, "波的農場", "成立於1990年的「波的農場」，農場主人程清波第一次看到豬籠草，被這種植物的環境適應力及堅強求生性所吸引，慢慢培養繁殖的過程中，他不但愈來愈感興趣，也因此決定進一步以食蟲植物為主角、轉作休閒農場。"));
        introList.add(new IntroductionData(null, "甚麼是食蟲植物?", "食蟲植物是指能夠誘捕昆蟲、小動物，能分泌消化液將其消化以補充自身養分的植物。如豬籠草和捕蠅草等。有些食蟲器官較大者甚至可捕食小型蛙類、蜥蜴和鳥。\n"));
        introList.add(new IntroductionData(R.drawable.d7, "豬籠草", "多年生草本植物，主產地是熱帶亞洲地區。\n" +
                "有獨特的吸取營養的器官—捕蟲籠，捕蟲籠呈圓筒形，形狀像豬籠，故稱豬籠草。\n"));
        introList.add(new IntroductionData(R.drawable.d2, "捕蠅草", "捕蠅草其變態葉，不但擁有捕食昆蟲的功能，外觀明顯的刺毛以及紅色的無柄腺部位，看起來就像是長著利牙的血盆大口。\n" +
                "葉片邊緣也像的睫毛一般。"));
        introList.add(new IntroductionData(R.drawable.d3, "毛氈苔", "毛氈苔的外觀纖細柔弱，捕蟲葉上佈滿腺毛，腺毛上又有消化昆蟲的黏液，在光線照射下，植株表面覆滿一層晶瑩剔透的水滴。\n" +
                "植株較小、不佔空間，很適合作為收集品種的目標。"));
        introList.add(new IntroductionData(R.drawable.d4, "瓶子草花", "瓶子草的葉特化成漏斗狀的陷阱，並產生「酶」來分解掉進去的獵物。昆蟲會被瓶口以及瓶蓋密腺所分泌的蜜汁吸引，加上捕蟲瓶上部經陽光照射後如彩繪玻璃般，也會誘使昆蟲前往。"));
        introList.add(new IntroductionData(null, "波蜂捉蠅", "遊戲說明:\n" +
                "\n" +
                "玩家們變成食蟲植物，當昆蟲碰到植物請點選植物進行補食。\n" +
                "\n" +
                "15秒內請補食10隻昆蟲，即可過關。\n" +
                "\n"));
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

                Intent intent = new Intent(InsectGameDepiction.this, InsectGame.class);
                startActivity(intent);
                finish();
            }
        });
    }
}