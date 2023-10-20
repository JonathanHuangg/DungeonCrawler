package com.example.dungencrawler.model;

public class PlayerMovementDown implements EntityStrategy, Subscriber {
    @Override
    public void execute(Player player) {
        if (player.getPlayerY() < 750) {
            player.setPlayerY(player.getPlayerY() + 20);
        }
    }
    @Override
    public void update(Player player) {
        execute(player);
    }
}
