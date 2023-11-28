package com.example.dungencrawler.model;

public class PowerUpInstaWin extends PowerUpDecorator implements PowerUp {

    private float x;
    private float y;
    public PowerUpInstaWin(PlayerInterface decoratedPlayer, float x, float y) {
        super(decoratedPlayer);
        this.x = x;
        this.y = y;
    }

    @Override
    public void setPlayerWinResult(int score) {
        super.setPlayerWinResult(100);
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }
}
