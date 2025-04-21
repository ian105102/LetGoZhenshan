package com.example.letgozhenshan.Stage1;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.letgozhenshan.MainMap;
import com.example.letgozhenshan.QA.QaSystem;
import com.example.letgozhenshan.QA.Question;
import com.example.letgozhenshan.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceGame extends AppCompatActivity  implements QaSystem.AnswerResultListener {

    Button mButton1 , mButton2, mButton3, mButton4;
    TextView mTitleText , mDepiction , mPointText;
    QaSystem mServiceCenterQa;
    List<Question>  mQuestionList;

    int Point = 0;
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_service_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // 什麼都不做，直接忽略返回鍵
            }
        });
        mQuestionList = new ArrayList<>();
        mQuestionList.add(new Question(
                "1. 哪種水果不是枕頭山的特產？\n" ,

                Arrays.asList("A. 紅心芭樂", "B. 金棗", "C. 鳳梨", "D. 蓮霧"),
                2
                ));
        mQuestionList.add(new Question(
                "請問全台金棗年產量中，大約百分之幾來自宜蘭?？",
                Arrays.asList("A. 90%", "B. 85%", "C. 80%", "D. 75%"),
                0
                ));
        mQuestionList.add(new Question(
                "3. 望龍埤成為著名景點，是因為哪個電視劇？",
                Arrays.asList("A. 命中註定我愛你", "B. 下一站，幸福", "C. 惡作劇之吻", "D. 海派甜心" ),
                1
               ));
        mQuestionList.add(new Question(
                "4. 阿蘭城湧泉，水溫長年恆溫為幾度？",
                Arrays.asList("A. 15", "B. 18", "C. 22", "D. 26" ),
                2
        ));
        mQuestionList.add(new Question(
                "5. 枕頭山是哪個傳統技藝的發源地？",
                Arrays.asList("A. 皮影戲", "B. 歌劇", "C. 詠嘆調", "D. 歌仔戲" ),
                3
        ));
        mButton1 = findViewById(R.id.button6);
        mButton2 = findViewById(R.id.button7);
        mButton3 = findViewById(R.id.button8);
        mButton4 = findViewById(R.id.button9);
        mPointText = findViewById(R.id.textView14);
        mTitleText = findViewById(R.id.textView7);
        mDepiction = findViewById(R.id.textView8);
        mServiceCenterQa =  new QaSystem(mQuestionList,
                new Button[]{mButton1, mButton2, mButton3, mButton4},
                mTitleText,
                mDepiction
            ,this
        ,R.drawable.rounded_gradient_border_button
        );
        mServiceCenterQa.setOnAnswerResultListener(this);




    }

    @Override
    public void onAnswer(boolean isCorrect) {
        if(isCorrect){
            Point++;
            mPointText.setText("答對題數:"+ Point +"/"+mQuestionList.size());
        }
    }

    @Override
    public void onEnd() {
        boolean isWin = Point>=4;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("遊戲結束");
        builder.setMessage(isWin ? "你贏了！按下繼續前往下一關" : "你輸了...按下繼續前往下一關！");
        if(isWin){
            MainMap.Point++;
        }
        builder.setPositiveButton("繼續", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainMap.Pass+=1;
                MainMap.PassList.set(0, true);
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);  // 禁止按返回鍵關閉
        dialog.show();
    }

}