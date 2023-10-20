package com.example.dungencrawler.model;
public class PlayerMovementDown implements EntityStrategy {

    @Override
    public void execute(Player player, int screenHeight, int screenWidth) {
        if (player.getPlayerY() < screenHeight - 175) {
            player.setPlayerY(player.getPlayerY() + 15);
        }
    }
}
