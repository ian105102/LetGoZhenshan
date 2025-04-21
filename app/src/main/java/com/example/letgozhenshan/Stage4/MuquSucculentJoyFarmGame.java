package com.example.letgozhenshan.Stage4;

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

public class MuquSucculentJoyFarmGame extends AppCompatActivity implements QaSystem.AnswerResultListener {
    Button mButton1 , mButton2, mButton3, mButton4;
    TextView mTitleText , mDepiction ,  mPointText;
    QaSystem mServiceCenter;
    List<Question> mQuestionList;
    int Point =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_muqu_succulent_joy_farm);
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
                "1. 木渠多肉樂活農場位於哪個休閒農業區？\n" +
                        "A. 枕頭山休閒農業區\n" +
                        "B. 望龍埤農業區\n" +
                        "C. 太平山休閒農業區\n" +
                        "D. 福山農業區",
                Arrays.asList("A", "B", "C", "D"),
                0
        ));
        mQuestionList.add(new Question(
                "2. 木渠多肉樂活農場鄰近哪個著名景點？\n" +
                        "A. 太平山國家森林遊樂區\n" +
                        "B. 望龍埤\n" +
                        "C. 明池森林遊樂區\n" +
                        "D. 羅東夜市",
                Arrays.asList("A", "B", "C", "D"),
                1
        ));
        mQuestionList.add(new Question(
                "3.哪種環境最適合多肉植物生長？\n" +
                        "A. 潮濕陰暗的地方\n" +
                        "B. 陽光充足、排水良好的地方\n" +
                        "C. 冷氣房內\n" +
                        "D. 完全不澆水的環境",
                Arrays.asList("A", "B", "C", "D"),
                1
        ));
        mQuestionList.add(new Question(
                "4. 多肉植物繁殖最常見的方法是？\n" +
                        "A. 播種\n" +
                        "B. 扦插\n" +
                        "C. 水培\n" +
                        "D. 移植整株",
                Arrays.asList("A", "B", "C", "D"),
                1
        ));
        mQuestionList.add(new Question(
                "5. 哪些不是多肉植物?\n",
                Arrays.asList(R.drawable.q0,R.drawable.q1, R.drawable.q2,R.drawable.q3),
                1
        ));

        mButton1 = findViewById(R.id.button6);
        mButton2 = findViewById(R.id.button7);
        mButton3 = findViewById(R.id.button8);
        mButton4 = findViewById(R.id.button9);
        mPointText = findViewById(R.id.textView10);
        mTitleText = findViewById(R.id.textView7);
        mDepiction = findViewById(R.id.textView8);
        mServiceCenter =  new QaSystem(mQuestionList,
                new Button[]{mButton1, mButton2, mButton3, mButton4},
                mTitleText,
                mDepiction
                ,this
        ,R.drawable.buttonimge
        );
        mServiceCenter.setOnAnswerResultListener(this);

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
                MainMap.PassList.set(3, true);
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);  // 禁止按返回鍵關閉
        dialog.show();
    }
}