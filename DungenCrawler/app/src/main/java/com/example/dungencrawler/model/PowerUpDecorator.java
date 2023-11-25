package com.example.dungencrawler.model;

import java.util.List;

public abstract class PowerUpDecorator implements PlayerInterface {
    protected PlayerInterface decoratedPlayer;

    public PowerUpDecorator(PlayerInterface decoratedPlayer) {
        this.decoratedPlayer = decoratedPlayer;
    }

    @Override
    public String getName() {
        return decoratedPlayer.getName();
    }

    @Override
    public int getHealth() {
        return decoratedPlayer.getHealth();
    }

    @Override
    public float getPlayerX() {
        return decoratedPlayer.getPlayerX();
    }

    @Override
    public float getPlayerY() {
        return decoratedPlayer.getPlayerY();
    }

    @Override
    public EntityStrategy getEntityStrategy() {
        return decoratedPlayer.getEntityStrategy();
    }

    @Override
    public List<Subscriber> getSubscribers() {
        return decoratedPlayer.getSubscribers();
    }

    @Override
    public int getAttackStatus() {
        return decoratedPlayer.getAttackStatus();
    }

    @Override
    public boolean getPlayerWinResult() {
        return decoratedPlayer.getPlayerWinResult();
    }

    @Override
    public void setEntityStrategy(EntityStrategy entityStrategy) {
        decoratedPlayer.setEntityStrategy(entityStrategy);
    }

    @Override
    public void setName(String name) {
        decoratedPlayer.setName(name);
    }

    @Override
    public void setAttackStatus(int status) {
        decoratedPlayer.setAttackStatus(status);
    }

    @Override
    public void setHealth(int health) {
        decoratedPlayer.setHealth(health);
    }

    @Override
    public void setPlayerX(float x) {
        decoratedPlayer.setPlayerX(x);
    }

    @Override
    public void setPlayerY(float y) {
        decoratedPlayer.setPlayerY(y);
    }

    @Override
    public void setPlayerWinResult(int score) {
        decoratedPlayer.setPlayerWinResult(score);
    }

    @Override
    public void executeEntityStrategy(Player player, int screenHeight, int screenWidth) {
        decoratedPlayer.executeEntityStrategy(player, screenHeight, screenWidth);
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        decoratedPlayer.subscribe(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        decoratedPlayer.unsubscribe(subscriber);
    }
}
