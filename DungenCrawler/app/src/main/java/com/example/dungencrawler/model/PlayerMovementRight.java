package com.example.dungencrawler.model;

public class PlayerMovementRight implements EntityStrategy, Subscriber {
    @Override
    public void execute(Player player, int screenHeight, int screenWidth) {
        if (player.getPlayerX() < screenWidth - 100) {
            player.setPlayerX(player.getPlayerX() + 20);
        }
    }
    @Override
    public void update(Player player, int screenHeight, int sceenWidth) {
        execute(player, screenHeight, sceenWidth);
    }
}
