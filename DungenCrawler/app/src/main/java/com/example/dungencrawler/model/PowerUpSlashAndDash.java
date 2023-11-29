package com.example.dungencrawler.model;

public class PowerUpSlashAndDash extends PowerUpDecorator implements PowerUp {
    private static final float DASH_DISTANCE = 800.0f;
    private static final int ATTACK = 1;
    private static final int NOTATTACK = 0;

    private boolean isDashing = false;
    private float dashSpeed = 30.0f; // Units per update, adjust based on your game's frame rate
    private float targetX;

    private float x; // manually put these in
    private float y;

    public PowerUpSlashAndDash(PlayerInterface decoratedPlayer, float x, float y) {

        super(decoratedPlayer);
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    // Call this method to start the dash
    public void startDash() {
        if (!isDashing) {
            isDashing = true;
            targetX = decoratedPlayer.getPlayerX() + DASH_DISTANCE;
            decoratedPlayer.setAttackStatus(ATTACK);
        }
    }

    // Call this method from your onTick to update the dash
    public void updateDash() {
        if (isDashing) {
            float currentPosition = decoratedPlayer.getPlayerX();
            if (currentPosition < targetX) {
                currentPosition += dashSpeed;
                currentPosition = Math.min(currentPosition, targetX);
                decoratedPlayer.setPlayerX(currentPosition);
            } else {
                // End the dash
                isDashing = false;
                decoratedPlayer.setAttackStatus(NOTATTACK);
            }
        }
    }

    public void setIsDashing(boolean bool) {
        isDashing = bool;
    }
}
