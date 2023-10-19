package com.example.dungencrawler.model;

public class PlayerMovementRight implements EntityStrategy {
    @Override
    public void execute(Player player) {
        if (player.getPlayerX() < 2100) {
            player.setPlayerX(player.getPlayerX() + 20);
        }
    }
}
