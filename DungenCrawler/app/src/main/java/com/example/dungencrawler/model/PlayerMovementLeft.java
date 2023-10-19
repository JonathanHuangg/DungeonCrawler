package com.example.dungencrawler.model;

public class PlayerMovementLeft implements EntityStrategy {
      @Override
    public void execute(Player player, String direction) {
          player.setPlayerY(player.getPlayerX() - 50);
      }
}
//    @Override
//    public void moveUp(Player player) {
//        player.setPlayerY(player.getPlayerY() + 50);
//    }
//
//    @Override
//    public void moveDown(Player player) {
//        player.setPlayerY(player.getPlayerY() - 50);
//    }
//
//    @Override
//    public void moveLeft(Player player) {
//        player.setPlayerX(player.getPlayerY() - 50);
//    }
//
//    @Override
//    public void moveRight(Player player) { player.setPlayerX(player.getPlayerY() + 50); }
