package com.example.dungencrawler.model;

public class PowerUpSlashAndDash extends PowerUpDecorator implements PowerUp{
    private static final float DASH_DISTANCE = 20.0f;
    private static final int ATTACK = 1;
    private static final int NOTATTACK = 0;
    private float dashSpeed = 5.0f; // Units per update, adjust based on your game's frame rate

    private float x; // manually put these in
    private float y;

    public PowerUpSlashAndDash(PlayerInterface decoratedPlayer, float x, float y) {

        super(decoratedPlayer);
        this.x = x;
        this.y = y;
    }

    @Override
    public void setPlayerX(float x) {
        // Set the player to attacking status
        decoratedPlayer.setAttackStatus(ATTACK);

        // Start dash
        dash(x);
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    private void dash(float startX) {
        float targetX = startX + DASH_DISTANCE;
        new Thread(() -> {
            float currentPosition = startX;
            while (currentPosition < targetX) {
                currentPosition += dashSpeed;
                // Make sure we don't go past the target position
                currentPosition = Math.min(currentPosition, targetX);

                // move the player
                decoratedPlayer.setPlayerX(currentPosition);

                // Glide movement
                try {
                    Thread.sleep(50); // Adjust sleep duration for desired speed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Reset attack status here or trigger an event to do so
            decoratedPlayer.setAttackStatus(NOTATTACK);
        }).start();
    }
}
