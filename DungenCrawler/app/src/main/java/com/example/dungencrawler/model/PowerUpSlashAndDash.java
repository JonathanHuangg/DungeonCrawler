package com.example.dungencrawler.model;

public class PowerUpSlashAndDash extends PowerUpDecorator {
    private static final float DASH_DISTANCE = 20.0f;
    private static final int ATTACKING_STATUS = 1;
    private boolean isDashing = false;

    public PowerUpSlashAndDash(PlayerInterface decoratedPlayer) {
        super(decoratedPlayer);
    }

    @Override
    public void setPlayerX(float x) {
        if (!isDashing) {
            startDash(x);
        }
    }

    private void startDash(float startX) {
        isDashing = true;
        decoratedPlayer.setAttackStatus(ATTACKING_STATUS);

        float targetX = startX + DASH_DISTANCE;
        // Assuming a simple linear movement for the dash
        decoratedPlayer.setPlayerX(targetX);

        // You might need a mechanism to set the attack status back to normal
        // after the dash is complete. This could be done with a timer or an update loop.

        isDashing = false;
    }
}
