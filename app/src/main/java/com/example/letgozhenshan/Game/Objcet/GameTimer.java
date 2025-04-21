package com.example.letgozhenshan.Game.Objcet;

public class GameTimer {

    private long startTime = -1;
    private long lastUpdateTime = -1;
    private int elapsedSeconds = 0;
    private int durationSeconds = 0;
    private boolean isRunning = false;

    private Runnable onTick = null;
    private Runnable onFinish = null;

    public GameTimer(int durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public void start() {
        isRunning = true;
        startTime = System.currentTimeMillis();
        lastUpdateTime = startTime;
        elapsedSeconds = 0;
    }

    public void stop() {
        isRunning = false;
    }

    public void reset() {
        startTime = -1;
        lastUpdateTime = -1;
        elapsedSeconds = 0;
        isRunning = false;
    }


    public void update() {
        if (!isRunning) return;

        long currentTime = System.currentTimeMillis();
        long deltaTime = currentTime - lastUpdateTime;

        if (deltaTime >= 1000) {
            int secondsPassed = (int) (deltaTime / 1000);
            elapsedSeconds += secondsPassed;
            lastUpdateTime += secondsPassed * 1000;

            if (onTick != null) {
                onTick.run();
            }

            if (elapsedSeconds >= durationSeconds) {
                isRunning = false;
                if (onFinish != null) {
                    onFinish.run();
                }
            }
        }
    }

    public void setOnTick(Runnable onTick) {
        this.onTick = onTick;
    }

    public void setOnFinish(Runnable onFinish) {
        this.onFinish = onFinish;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public int getElapsedSeconds() {
        return elapsedSeconds;
    }
    public int getRemainingSeconds() {
        return Math.max(0, durationSeconds - elapsedSeconds);
    }
}
