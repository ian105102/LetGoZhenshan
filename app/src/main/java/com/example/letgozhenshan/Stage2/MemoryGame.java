package com.example.letgozhenshan.Stage2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.letgozhenshan.Introduction.IntroductionData;
import com.example.letgozhenshan.MainMap;
import com.example.letgozhenshan.MemoryGame.Card;
import com.example.letgozhenshan.MemoryGame.MemoryGameListener;
import com.example.letgozhenshan.MemoryGame.MemoryGameSystem;
import com.example.letgozhenshan.R;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MemoryGame extends AppCompatActivity implements MemoryGameListener {

    private FlexboxLayout mGameMainLayout;
    private MemoryGameSystem mMemoryGmaeSystem;
    private int pairCount = 10;
    private List<Button> mCardButtons = new ArrayList<>();
    private Map<Integer, IntroductionData> mTypeToDrawable;
    private Boolean isEnd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_memory_game);
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
        mTypeToDrawable = new HashMap<>();
        mTypeToDrawable.put(0,
                new IntroductionData(
                        R.drawable.flower1
                        , "文心蘭"
                        , "又稱跳舞蘭，因為它的花在綻放時看起來好像穿著舞裙、伸展雙臂的舞女。\n" +
                        "花朵色彩有純黃、洋紅、粉紅、茶褐色花紋、斑點等多種變化，其花序更是變化多端，有豎立開一、二朵花至數十、數百朵者不一而足；花期因品種不同而互異。"
                )
        );
        mTypeToDrawable.put(1,
                new IntroductionData(
                        R.drawable.flower2
                        , "非洲菫"
                        , "非洲蓳容易開花，植株又小巧，生長強健，喜好的環境剛好與室內相同。\n" +
                        "毛絨絨又肥厚的葉片，非洲蓳容易開花，植株又小巧，生長強健，喜好的環境剛好與室內相同。\n" +
                        "小巧的花朵在花型和花色上更是多變，還有鑲邊、漸層等花色變化。"
                ));
        mTypeToDrawable.put(2,
                new IntroductionData(
                        R.drawable.flower3
                        , "石斛蘭"
                        , "春石斛花芽分化必須接受冬季低溫，所以開花集中於春季，臺灣中北部栽培多，由於花莖較短，常以盆花出售。\n" +
                        "秋石斛蘭，在溫暖潮濕的環境下並接受短日照的刺激花芽，所以開花集中於秋季，常見於臺灣中南部，其花朵類似蝴蝶蘭較為圓滿，色澤變化多。"
                ));

        mTypeToDrawable.put(3,
                new IntroductionData(
                        R.drawable.flower4
                        , "空氣鳳梨"
                        , "屬於鳳梨科，是一般食用鳳梨的近親。\n" +
                        "葉片上長有會吸水的細毛，葉面的鱗片狀組織能夠吸收雨水或吸收空氣中的濕氣，是真正吸收水和養分的器官。\n" +
                        "它不需要土壤來吸取養份，大多數只要透過空氣得以生存。"
                ));
        mTypeToDrawable.put(4,
                new IntroductionData(
                        R.drawable.flower5
                        , "長壽花"
                        , "景天科，多年生草本。\n" +
                        "長壽花有厚革質的葉片，可以減緩水分蒸散；粗大的莖部能夠儲藏水分與養分；鬚根發達吸收能力強，但是相對的也很怕浸水潮溼。\n" +
                        "花朵色濃質厚，歷久不凋，所以有「長壽」的美稱。"
                ));
        mTypeToDrawable.put(5,
                new IntroductionData(
                        R.drawable.flower6
                        , "非洲菊"
                        , "花色亮麗多彩，猶如一輪豔陽，又有\"太陽紅\"之稱，外觀有點狀似小型的向日葵。\n" +
                        "一般多用做切花使用，四季皆能供應，近年有不少較為矮性的品種，可做成居家盆花或一般花壇栽種。"
                ));
        mTypeToDrawable.put(6,
                new IntroductionData(
                        R.drawable.flower7
                        , "常春藤"
                        , "多年生常綠藤本觀葉植物，是常見的庭園造景綠化植物。\n" +
                        "葉色濃綠，不同品種的葉片會有相異的斑紋或斑塊。\n" +
                        "作為室內綠化裝飾時，可攀附或懸掛在牆上。"
                ));
        mTypeToDrawable.put(7,
                new IntroductionData(
                        R.drawable.flower8
                        , "龜背芋"
                        , "天南星科龜背芋屬。\n" +
                        "是一種攀緣性的附生植物，幼態時葉子為心葉形，隨著成熟逐漸裂葉、開孔，相當好照顧，適合室內種植。\n"
                ));
        mTypeToDrawable.put(8,
                new IntroductionData(
                        R.drawable.flower9
                        , "網紋草"
                        , "一種多年生常綠草本，植株低矮，花梗被絨毛緊密覆蓋，且葉片上具有紅色或白色葉脈，縱橫交替成明顯的網紋。\n" +
                        "由於它精巧玲瓏，夜色淡雅，是作為盆栽的極佳選擇。"
                ));
        mTypeToDrawable.put(9,
                new IntroductionData(
                        R.drawable.flower10
                        , "鹿角蕨"
                        , "為附生形氣生植物，植株多呈懸垂性，附生於蛇木柱，樹幹等。\n" +
                        "根莖部位短而肥厚，基部可見黑褐色的鱗片，是附著他物生長的部位。\n" +
                        "葉背能生長孢子囊群，懸垂性，形似鹿角，全葉披覆一層柔毛。"
                ));
        mGameMainLayout = findViewById(R.id.flexLayout);
        mMemoryGmaeSystem = new MemoryGameSystem(pairCount, this);


        initButtons(mMemoryGmaeSystem.getCards());
        mMemoryGmaeSystem.startPreview(3);
    }

    public void initButtons(List<Card> cards) {
        mCardButtons.clear();
        mGameMainLayout.removeAllViews();

        for (Card card : cards) {
            Button btn = new Button(this);

            FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(
                    200, 300
            );
            params.setMargins(15, 15, 15, 15);
            btn.setLayoutParams(params);
            btn.setPadding(8, 8, 8, 8);
            btn.setTag(card.id); // 把卡片 id 存起來

            btn.setOnClickListener(v -> {
                int id = (int) v.getTag();
                mMemoryGmaeSystem.selectCard(id);
            });

            mCardButtons.add(btn);
            mGameMainLayout.addView(btn);
        }
    }
    @Override
    public void onUpdateUI(List<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            Button btn = mCardButtons.get(i);
            Integer resId = mTypeToDrawable.get(card.type).getImageResId();


            if (card.isFlipped || card.isMatched) {
                // 顯示對應的正面圖
                setCroppedBackgroundImage(btn, resId);

//                btn.setText(card.type+"");
            } else {
                // 顯示背面圖
                btn.setBackgroundResource(R.drawable.ic_launcher_background);
//                btn.setText(card.type+"");
            }
            btn.setEnabled(!card.isMatched); // 已配對的就不能再點
        }
    }
    public void setCroppedBackgroundImage(final View view, @DrawableRes final int resId) {
        view.post(new Runnable() {
            @Override
            public void run() {
                int viewWidth = view.getWidth();
                int viewHeight = view.getHeight();

                if (viewWidth == 0 || viewHeight == 0) return;

                // 讀取圖片
                Bitmap original = BitmapFactory.decodeResource(view.getResources(), resId);
                if (original == null) return;

                int imgWidth = original.getWidth();
                int imgHeight = original.getHeight();

                // 計算縮放比例（centerCrop）
                float scale = Math.max((float) viewWidth / imgWidth, (float) viewHeight / imgHeight);
                int scaledWidth = Math.round(imgWidth * scale);
                int scaledHeight = Math.round(imgHeight * scale);

                // 放大圖片
                Bitmap scaled = Bitmap.createScaledBitmap(original, scaledWidth, scaledHeight, true);

                // 裁切圖片中心
                int x = (scaledWidth - viewWidth) / 2;
                int y = (scaledHeight - viewHeight) / 2;
                Bitmap cropped = Bitmap.createBitmap(scaled, x, y, viewWidth, viewHeight);

                // 設定為背景
                Drawable drawable = new BitmapDrawable(view.getResources(), cropped);
                view.setBackground(drawable);
            }
        });
    }
    @Override
    public void onMatchSuccess(Card c1, Card c2) {
        Toast.makeText(this, "配對成功！", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, FlowerIntroduction.class);
        intent.putExtra("intro_data", mTypeToDrawable.get(c1.type)); // 傳送 Serializable 物件
        startActivity(intent);

    }

    @Override
    public void onMatchFail(Card c1, Card c2) {
        Toast.makeText(this, "配對失敗～", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGameEnd() {


        isEnd = true;



    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isEnd){



            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("遊戲結束");
            builder.setMessage("你贏了！按下繼續前往下一關" );

            builder.setPositiveButton("繼續", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainMap.Pass+=1;
                    MainMap.Point++;
                    MainMap.PassList.set(1, true);
                    finish();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.setCancelable(false);  // 禁止按返回鍵關閉
            dialog.show();
        }

    }
}
