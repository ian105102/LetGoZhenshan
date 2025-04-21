package com.example.letgozhenshan.Introduction;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class IntroductionSystem {

    private ImageView imageView;
    private TextView titleTextView;
    private TextView contentTextView;
    private Button prevButton;
    private Button nextButton;
    private List<IntroductionData> dataList;

    private int currentIndex = 0;
    private boolean endTriggered = false;
    private Runnable onEndReached = null;
    public interface OnEndReachedCallback {
        void onEndReached();
    }
    public IntroductionSystem(ImageView imageView, TextView titleTextView, TextView contentTextView,
                              Button prevButton, Button nextButton, List<IntroductionData> dataList) {
        this.imageView = imageView;
        this.titleTextView = titleTextView;
        this.contentTextView = contentTextView;
        this.prevButton = prevButton;
        this.nextButton = nextButton;
        this.dataList = dataList;

        setupButtons();
        updateUI();
    }
    private OnEndReachedCallback onEndReachedCallback;
    public void setOnEndReachedCallback(OnEndReachedCallback callback) {
        this.onEndReachedCallback = callback;
    }

    private void setupButtons() {
        prevButton.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                endTriggered = false; // 重置狀態
                updateUI();
            }
        });

        nextButton.setOnClickListener(v -> {
            if (currentIndex < dataList.size() - 1) {
                currentIndex++;
                updateUI();
            } else {
                if (!endTriggered && onEndReachedCallback != null) {
                    endTriggered = true;

                } else {
                    onEndReachedCallback.onEndReached();
                    nextButton.setEnabled(false); // 第二次才真正禁用按鈕
                }
            }
        });
    }

    private void updateUI() {
        IntroductionData currentData = dataList.get(currentIndex);

        if (currentData.hasImage()) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(currentData.getImageResId());
        } else {
            imageView.setVisibility(View.GONE);
        }

        titleTextView.setText(currentData.getTitle());
        contentTextView.setText(currentData.getContent());

        prevButton.setEnabled(currentIndex > 0);
        // 下一頁永遠啟用，交由點擊判斷是否觸發結尾事件
        nextButton.setEnabled(true);
    }
}
