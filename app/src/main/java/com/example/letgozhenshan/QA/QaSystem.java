package com.example.letgozhenshan.QA;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.letgozhenshan.R;

import java.util.List;

public class QaSystem {

    private final List<Question> questions;
    private int currentIndex = 0;

    private final Button[] buttons;
    private final TextView titleText;
    private final TextView questionText;
    private final Context context;
    private int BackgroundImge = R.drawable.rounded_gradient_border_button ;

    private AnswerResultListener resultListener;

    public interface AnswerResultListener {
        void onAnswer(boolean isCorrect);
        void onEnd();
    }

    public QaSystem(List<Question> questions, Button[] buttons, TextView titleText, TextView questionText, Context context ,int id) {
        this.questions = questions;
        this.buttons = buttons;
        this.titleText = titleText;
        this.questionText = questionText;
        this.context = context;
        this.BackgroundImge = id;
        setupButtonClickListeners();
        updateUI();
    }

    private void setupButtonClickListeners() {
        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            buttons[i].setOnClickListener(v -> handleAnswer(index));
        }
    }

    private void updateUI() {
        if (currentIndex >= questions.size()) {
            if (resultListener != null) {
                resultListener.onEnd();
            }
            titleText.setText("題目已完成！");
            questionText.setText("即將結算!!");
            for (Button b : buttons) b.setEnabled(false);
            return;
        }

        Question q = questions.get(currentIndex);
        titleText.setText("第 " + (currentIndex + 1) + " 題");
        questionText.setText(q.getQuestion());

        resetButtonColors();

        for (int i = 0; i < buttons.length; i++) {
            Object option = q.getOptions().get(i);

            if (option instanceof String) {
                buttons[i].setText((String) option);
                buttons[i].setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0); // 清除圖片
            } else if (option instanceof Drawable) {
                buttons[i].setText(""); // 隱藏文字
                buttons[i].setCompoundDrawablesWithIntrinsicBounds(null, (Drawable) option, null, null); // 顯示圖片在上方
            } else if (option instanceof Integer) {
                // 將資源 ID 轉成 Drawable
                int resId = (Integer) option;
                buttons[i].setText(""); // 隱藏文字
                buttons[i].setBackgroundResource(resId); // 顯示圖片在上方

            }
        }
    }

    private void resetButtonColors() {


        for (Button button : buttons) {
            button.setBackgroundResource(BackgroundImge);
            button.setEnabled(true);
            button.setTextColor(context.getResources().getColor(android.R.color.black));  // 設置預設文字顏色
        }
    }

    private void handleAnswer(int selectedIndex) {
        Question q = questions.get(currentIndex);
        boolean isCorrect = selectedIndex == q.getCorrectIndex();

        disableButtons();

        changeButtonColors(isCorrect, selectedIndex);

        if (resultListener != null) {
            resultListener.onAnswer(isCorrect);
        }

        new Handler().postDelayed(() -> {
            currentIndex++;
            updateUI();
        }, 1000);
    }

    private void disableButtons() {
        for (Button button : buttons) {
            button.setEnabled(false);
        }
    }

    private void changeButtonColors(boolean isCorrect, int selectedIndex) {
        int correctColor = context.getResources().getColor(R.color.correct_color);
        int wrongColor = context.getResources().getColor(R.color.wrong_color);

        for (int i = 0; i < buttons.length; i++) {
            if (i == questions.get(currentIndex).getCorrectIndex()) {
                buttons[i].setBackgroundColor(correctColor);
            } else if (i == selectedIndex) {
                buttons[i].setBackgroundColor(wrongColor);
            }
        }
    }

    public void jumpToQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            currentIndex = index;
            updateUI();
        }
    }

    public void setOnAnswerResultListener(AnswerResultListener listener) {
        this.resultListener = listener;
    }

    public boolean isFinished() {
        return currentIndex >= questions.size();
    }
}
