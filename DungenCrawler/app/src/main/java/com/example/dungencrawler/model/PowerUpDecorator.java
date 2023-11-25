package com.example.dungencrawler.model;

import java.util.List;

public abstract class PowerUpDecorator implements PowerUp {
    protected PowerUp decoratedPowerUp;

    public PowerUpDecorator(PowerUp decoratedPowerUp) {
        this.decoratedPowerUp = decoratedPowerUp;
    }

}
