
package com.example.dungencrawler.model;

public class PowerUpHealthDecorator extends PowerUpDecorator {
    int xPos;
    int yPos;

    public PowerUpHealthDecorator(PowerUp decoratedPowerUp) {
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


    // TODO: gonna comment this out for now, since I think we can do this in the Observer class
    // similarly to how enemy collisions work (j checking position).
    /*
    @Override
    public void setHealth(int health) {
        // Enhance the setHealth behavior
        super.setHealth(health + 100);
    }
    */
    // No need to override other methods if they don't add specific behaviors for this decorator
}