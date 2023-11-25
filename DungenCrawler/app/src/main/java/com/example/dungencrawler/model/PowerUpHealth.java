
package com.example.dungencrawler.model;

public class PowerUpHealth extends PowerUpDecorator {
    public PowerUpHealth(PlayerInterface decoratedPlayer) {
        super(decoratedPlayer);
    }

    @Override
    public void setHealth(int health) {
        // Enhance the setHealth behavior
        super.setHealth(health + 100);
    }

    // No need to override other methods if they don't add specific behaviors for this decorator
}