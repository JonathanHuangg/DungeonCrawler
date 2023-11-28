
package com.example.dungencrawler.model;


public class PowerUpHealth extends PowerUpDecorator implements PowerUp {
    private float x;
    private float y;
    public PowerUpHealth(PlayerInterface decoratedPlayer, float x, float y) {
        super(decoratedPlayer);
        this.x = x;
        this.y = y;
    }

    @Override
    public void setHealth(int health) {
        // Enhance the setHealth behavior
        super.setHealth(health + 100);
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }
}