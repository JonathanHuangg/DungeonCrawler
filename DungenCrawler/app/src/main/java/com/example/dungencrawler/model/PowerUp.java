package com.example.dungencrawler.model;

public interface PowerUp {
    /**
     * sets the location of the powerUp, used for collision detection with player
     * @param x
     * @param y
     */
    void setLocation(int x, int y);

    /**
     * @return the current type of powerup
     */
    String getType();

    int getAmount();

    /**
     * sets the amount to powerup, lets us scale. Amount can change between types of
     * powerups, difficulty modes, etc.
     * @param newAmount
     */
    void setAmount(int newAmount);

    boolean getStatus();

    /**
     * sets the status of the powerup; if false, player interactions don't do anything
     * if true, then the bonus is applied.
     * @param newStatus
     */
    void setStatus(boolean newStatus);

}
