package com.example.letgozhenshan.Game.Objcet;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.letgozhenshan.Game.HitBox.BoxHitBox;
import com.example.letgozhenshan.R;

public class Plant extends GameObject {

    private int screenWidth, screenHeight;
    public BoxHitBox Hitbox;
    private Bitmap sprite;
    private int Width , Height;
    private  Context context;


    public Plant(Context context, int screenWidth, int screenHeight , int IamgeID ,int x,int y) {
        super(0, 0);
        this.context = context;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        Width =x;
        Height =y;
        // 載入圖片資源（請確保你有一張 insect.png 放在 drawable 裡）
        sprite = BitmapFactory.decodeResource(context.getResources(),IamgeID);
        sprite = Bitmap.createScaledBitmap(sprite, Width, Height, true);
        Hitbox = new BoxHitBox();
        Hitbox.setPosition(0, 0);
        Hitbox.setSize(sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void update_() {
        Hitbox.setPosition(x, y);
    }

    @Override
    public void draw_(Canvas canvas) {
        if (sprite != null) {
            canvas.drawBitmap(sprite, 0-Width/2,0-Height/2, null); // 用 bitmap 畫出
        }
    }

    @Override
    public void afterDraw_(Canvas canvas) {
//        Hitbox.draw(canvas);
    }
}
