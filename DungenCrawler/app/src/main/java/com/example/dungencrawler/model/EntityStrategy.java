package com.example.dungencrawler.model;

public interface EntityStrategy {
    abstract void execute(Player player, int screenHeight, int screenWidth);
}
