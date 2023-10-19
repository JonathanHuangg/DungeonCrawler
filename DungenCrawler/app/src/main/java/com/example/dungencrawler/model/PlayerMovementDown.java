package com.example.dungencrawler.model;

public class PlayerMovementDown implements EntityStrategy {
    @Override
    public void execute(Player player, String direction) {
        player.setPlayerY(player.getPlayerY() - 50);
    }
}
