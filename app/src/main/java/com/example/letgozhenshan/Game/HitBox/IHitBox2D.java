package com.example.letgozhenshan.Game.HitBox;

import android.graphics.Canvas;

public interface IHitBox2D {


    void setPosition(float x, float y);
    void setSize(float width, float height) ;
    // 檢查兩個物件是否發生碰撞
    boolean checkCollision(IHitBox2D other);



    // 檢查一個點是否在碰撞框內
    boolean checkPointCollision(float pointX, float pointY);
    void  draw(Canvas canvas);
}