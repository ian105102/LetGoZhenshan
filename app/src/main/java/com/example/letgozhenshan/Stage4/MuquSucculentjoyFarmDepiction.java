package com.example.letgozhenshan.Stage4;

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

import java.util.ArrayList;
import java.util.List;

public class MuquSucculentjoyFarmDepiction extends AppCompatActivity {
    ImageView mImageView;
    TextView mTitleText , mContentText;
    Button mNextBtn , mBackBtn;
    IntroductionSystem mIntroductionSystem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_muqu_succulentjoy_farm_depiction);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        List<IntroductionData> introList = new ArrayList<>();
        introList.add(new IntroductionData(null, "木渠多肉樂活農場", "農場由女主人與其先生花費十多年時間精心打造，呈現出一個充滿療癒氛圍的多肉植物花園。 \n" +
                "還提供DIY多肉盆栽體驗活動，遊客可以在專業老師的指導下，親手組合屬於自己的多肉植物盆栽，享受創作的樂趣。 \n"));
        introList.add(new IntroductionData(null, "怎麼種植多肉?", "切忌過度澆水，因為過多水分會導致根部腐爛。需使用排水良好的土壤，以防積水。\n" +
                "\n" +
                "喜愛明亮的間接光，可將其置於有濾光效果的窗戶旁，或在室內提供明亮的間接光源。"));
        introList.add(new IntroductionData(R.drawable.d5, "如何挑選多肉?", "多肉植物的葉片會反映其儲藏水分的特性，因此挑選時可留意顏色鮮綠、葉片肥厚飽滿且富有彈力的多肉。"));
        introList.add(new IntroductionData(R.drawable.d6, "多肉的繁殖生長?", "其中有部分種類只要輕輕觸碰葉子即會掉落，將其放置便會冒出新芽，十分容易就能生長並分株；或是將子植株摘下插在土壤上種植。"));

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

                Intent intent = new Intent(MuquSucculentjoyFarmDepiction.this, MuquSucculentJoyFarmGame.class);
                startActivity(intent);
                finish();
            }
        });
    }
}