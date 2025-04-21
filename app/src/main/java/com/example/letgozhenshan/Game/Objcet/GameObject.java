package com.example.letgozhenshan.Game.Objcet;

import android.graphics.Canvas;

public class GameObject {

    protected float dx, dy;
    protected  float x, y;
    protected float rotation;
    protected float scaleX, scaleY;
    protected boolean isActive;


    public GameObject(float x, float y) {
        this.x = x;
        this.y = y;
        this.dy =0;
        this.dx =0;

        this.rotation = 0;
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        isActive = true;

    }
    public void setScale(float scaleX, float scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }
    public void setSpeed(float x, float y) {
        this.dx = x;
        this.dy = y;
    }
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public void setActive(boolean Active){
        isActive =Active;
    }
    public boolean getActive(){
        return isActive;
    }

    protected  void draw_(Canvas canvas){

    }
    protected  void afterDraw_(Canvas canvas){

    }
    protected  void update_(){

    }


    public void draw(Canvas canvas) {
        if(!isActive ) return;
        canvas.save();
        canvas.translate(x, y);
        canvas.rotate(rotation);
        canvas.scale(scaleX, scaleY);
        draw_(canvas);

        canvas.restore();

        afterDraw_(canvas);
    }
    public void update(){
        if(!isActive ) return;
        update_();


    }




}
