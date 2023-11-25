package com.example.dungencrawler.model;

public class PowerUpScoreDecorator extends PowerUpDecorator {
    int xPos;
    int yPos;

    public PowerUpScoreDecorator(PowerUp decoratedPowerUp) {
        super(decoratedPowerUp);
    }

    @Override
    public void setLocation(int x, int y) {
        this.xPos = x;
        this.yPos = y;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public int getAmount() {
        return 0;
    }

    @Override
    public void setAmount(int newAmount) {

    }

    @Override
    public boolean getStatus() {
        return false;
    }

    @Override
    public void setStatus(boolean newStatus) {

    }
}
