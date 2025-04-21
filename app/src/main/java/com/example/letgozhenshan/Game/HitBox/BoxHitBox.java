package com.example.letgozhenshan.Game.HitBox;


import android.graphics.Canvas;
import android.graphics.Paint;

public class BoxHitBox implements IHitBox2D {

    private float x, y;
    private float width, height;

    public BoxHitBox(){
        x=0;
        y=0;
        width=10;
        height=10;
    }
    public void setPosition(float x, float y) {
        this.x = x - width/2;
        this.y = y - height/2;
    }


    public float getX() {
        return x;
    }


    public float getY() {
        return y;
    }


    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    // 碰撞檢測：檢查兩個矩形是否重疊
    @Override
    public boolean checkCollision(IHitBox2D other) {
        // 如果傳入的是 BoxHitBox，進行矩形碰撞檢測
        if (other instanceof BoxHitBox) {
            BoxHitBox otherBox = (BoxHitBox) other;

            // 檢查兩個矩形是否有重疊
            return !(this.x + this.width < otherBox.getX() ||  // this box is left of the other box
                    this.x > otherBox.getX() + otherBox.getWidth() ||  // this box is right of the other box
                    this.y + this.height < otherBox.getY() ||  // this box is above the other box
                    this.y > otherBox.getY() + otherBox.getHeight());  // this box is below the other box
        }
        return false; // 如果不是 BoxHitBox 的實例，則無法進行碰撞檢測
    }

    @Override
    public boolean checkPointCollision(float pointX, float pointY) {
        return (pointX >= x && pointX <= x + width && pointY >= y && pointY <= y + height);
    }
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(0xFFFF0000); // 純紅色，不透明
        paint.setStyle(Paint.Style.STROKE); // 畫邊框
        paint.setStrokeWidth(10); // 邊框寬度，可調整

        canvas.drawRect(x, y, x + width, y + height, paint);
    }
}
