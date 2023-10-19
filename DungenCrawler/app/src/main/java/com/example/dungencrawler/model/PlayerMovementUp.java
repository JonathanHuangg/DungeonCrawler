package com.example.dungencrawler.model;

public class PlayerMovementUp implements EntityStrategy {
    @Override
    public void execute(Player player) {
        if (player.getPlayerY() > 0) {
            player.setPlayerY(player.getPlayerY() - 20);
        }
    }
}
