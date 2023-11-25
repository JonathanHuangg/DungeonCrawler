package com.example.dungencrawler.model;

public abstract class PowerUpDecorator implements PlayerInterface {
    protected PlayerInterface decoratedPlayer;

    public PowerUpDecorator(PlayerInterface decoratedPlayer) {
        this.decoratedPlayer = decoratedPlayer;
    }
    // This will be a health powerUp
    @Override
    public void setHealth(int health) {
        decoratedPlayer.setHealth(health);
    }

    //This will be a dash powerUp while player is always attacking
    @Override
    public void setPlayerX(float x) {
        decoratedPlayer.setPlayerX(x);
    }
    @Override
    public void setPlayerY(float y) {
        decoratedPlayer.setPlayerY(y);
    }
    @Override
    public void setAttackStatus(int status) {
        decoratedPlayer.setAttackStatus(status);
    }

    //Secret PowerUp that wins you the game. Will be 100
    public void setPlayerWinResult(int result) {
        decoratedPlayer.setPlayerWinResult(result);
    }

}
