package com.example.letgozhenshan.Game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private IGameThread gameScene; // 使用你自己的介面 GameScene
    private boolean running;
    public static Canvas canvas;

    private final int TARGET_FPS = 60;
    private final long TARGET_TIME = 1000 / TARGET_FPS;

    public GameThread(SurfaceHolder surfaceHolder, IGameThread gameScene) {
        this.surfaceHolder = surfaceHolder;
        this.gameScene = gameScene;
    }

    public void setRunning(boolean isRunning) {
        running = isRunning;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    gameScene.update();
                    gameScene.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1_000_000;
            waitTime = TARGET_TIME - timeMillis;

            if (waitTime > 0) {
                try {
                    sleep(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
