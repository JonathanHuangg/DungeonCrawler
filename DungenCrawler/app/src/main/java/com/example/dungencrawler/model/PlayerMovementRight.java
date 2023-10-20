package com.example.dungencrawler.model;

public class PlayerMovementRight implements EntityStrategy, Subscriber {
    @Override
    public void execute(Player player, int screenHeight, int screenWidth) {
        if (player.getPlayerX() < screenWidth - 100) {
            player.setPlayerX(player.getPlayerX() + 15);
        }
    }
    @Override
    public void update(Player player) {
        execute(player);
    }
}
