package com.example.dungencrawler.model;

public class PlayerMovementLeft implements EntityStrategy {
      @Override
    public void execute(Player player) {
          if (player.getPlayerX() > 0) {
              player.setPlayerX(player.getPlayerX() - 20);
          }
      }
}
