package com.example.dungencrawler.model;

public class PlayerMovementUp implements EntityStrategy {
    @Override
    public void execute(Player player, int screenHeight, int screenWidth) {
        if (player.getPlayerY() > 0) {
            player.setPlayerY(player.getPlayerY() - 15);
        }
    }
}
