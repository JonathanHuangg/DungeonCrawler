package com.example.dungencrawler.model;

public class PlayerMovementDown implements EntityStrategy {
    @Override
    public void execute(Player player) {
        if (player.getPlayerY() < 750) {
            player.setPlayerY(player.getPlayerY() + 20);
        }
    }
}
