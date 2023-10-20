package com.example.dungencrawler.model;

public class PlayerMovementLeft implements EntityStrategy, Subscriber {
      @Override
    public void execute(Player player) {
          if (player.getPlayerX() > 0) {
              player.setPlayerX(player.getPlayerX() - 20);
          }
      }
    @Override
    public void update(Player player) {
        execute(player);
    }
}
