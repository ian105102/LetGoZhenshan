package com.example.letgozhenshan.Game.Objcet;

import android.graphics.Canvas;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.letgozhenshan.Game.HitBox.BoxHitBox;
import com.example.letgozhenshan.R; // 確保 import 了 R

public class Insect extends GameObject {

    private int screenWidth, screenHeight;
    public BoxHitBox Hitbox;
    private Bitmap sprite;
    private int Width , Height;

    public Insect(Context context, int screenWidth, int screenHeight ,int IamgeID) {
        super(0, 0);

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        Width =100;
        Height =100;
        // 載入圖片資源（請確保你有一張 insect.png 放在 drawable 裡）
        sprite = BitmapFactory.decodeResource(context.getResources(),IamgeID );
        sprite = Bitmap.createScaledBitmap(sprite, Width, Height, true);
        Hitbox = new BoxHitBox();

        Hitbox.setPosition(0, 0);
        Hitbox.setSize(sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void update_() {
        x += dx;
        y += dy;

        if (x <= 0 || x >= screenWidth - sprite.getWidth()) {
            dx = -dx;
        }

        if (y <= 0 || y >= screenHeight - sprite.getHeight()) {
            dy = -dy;
        }
        rotation = (float) Math.toDegrees(Math.atan2(dy, dx)+Math.PI/2);
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
