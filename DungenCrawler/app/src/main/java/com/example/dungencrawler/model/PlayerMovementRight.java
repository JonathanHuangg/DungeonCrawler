package com.example.dungencrawler.model;

public class PlayerMovementRight implements EntityStrategy {
    @Override
    public void execute(Player player, String direction) {
        player.setPlayerY(player.getPlayerX() + 50);
    }
}
