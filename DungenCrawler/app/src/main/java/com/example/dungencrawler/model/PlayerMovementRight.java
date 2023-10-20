package com.example.dungencrawler.model;

public class PlayerMovementRight implements EntityStrategy, Subscriber {
    @Override
    public void execute(Player player) {
        if (player.getPlayerX() < 2100) {
            player.setPlayerX(player.getPlayerX() + 20);
        }
    }
    @Override
    public void update(Player player) {
        execute(player);
    }
}
