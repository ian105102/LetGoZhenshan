package com.example.letgozhenshan.Stage2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.letgozhenshan.Introduction.IntroductionData;
import com.example.letgozhenshan.R;
//
public class FlowerIntroduction extends AppCompatActivity {

    ImageView mImageView;
    TextView mTitleText , mContentText;
    Button mButtonCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_flower_intorductiono);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        IntroductionData data = (IntroductionData) getIntent().getSerializableExtra("intro_data");


        mImageView = findViewById(R.id.imageView);
        mTitleText = findViewById(R.id.textView5);
        mContentText = findViewById(R.id.textView11);
        mButtonCancel = findViewById(R.id.button12);
        if(data== null) return ;
        mImageView.setImageResource(data.getImageResId());
        mTitleText.setText(data.getTitle());
        mContentText.setText(data.getContent());
        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}