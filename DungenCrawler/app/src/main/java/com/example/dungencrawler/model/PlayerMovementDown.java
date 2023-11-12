package com.example.dungencrawler.model;

public class PlayerMovementDown implements EntityStrategy, Subscriber {
    @Override
    public void execute(Player player, int screenHeight, int screenWidth) {
        if (player.getPlayerY() < screenHeight - 175) {
            player.setPlayerY(player.getPlayerY() + 20);
        }
    }
    @Override
    public void update(Player player, int screenHeight, int sceenWidth) {
        execute(player, screenHeight, sceenWidth);
    }
}
