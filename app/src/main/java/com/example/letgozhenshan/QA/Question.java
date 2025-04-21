package com.example.letgozhenshan.QA;

import android.graphics.drawable.Drawable;
import java.util.List;

public class Question {
    private final String question;
    private final List<Object> options;  // 可以儲存圖片或文字選項
    private final int correctIndex;

    public Question(String question, List<Object> options, int correctIndex) {
        this.question = question;
        this.options = options;
        this.correctIndex = correctIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<Object> getOptions() {
        return options;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }
}