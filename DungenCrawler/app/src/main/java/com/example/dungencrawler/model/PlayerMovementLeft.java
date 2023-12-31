package com.example.dungencrawler.model;

public class PlayerMovementLeft implements EntityStrategy, Subscriber {
    @Override
    public void execute(Player player, int screenHeight, int screenWidth) {
        if (player.getPlayerX() > 0) {
            player.setPlayerX(player.getPlayerX() - 20);
        }
    }
    @Override
    public void update(Player player, int screenHeight, int screenWidth) {
        execute(player, screenHeight, screenWidth);
    }
}
