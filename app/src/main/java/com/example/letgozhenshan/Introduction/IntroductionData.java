package com.example.letgozhenshan.Introduction;

import java.io.Serializable;

public class IntroductionData implements Serializable {
    private Integer imageResId; // 可以為 null 或 0 表示沒圖片
    private String title;
    private String content;

    public IntroductionData(Integer imageResId, String title, String content) {
        this.imageResId = imageResId;
        this.title = title;
        this.content = content;
    }

    public Integer getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean hasImage() {
        return imageResId != null && imageResId != 0;
    }
}