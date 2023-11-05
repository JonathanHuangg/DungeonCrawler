package com.example.dungencrawler.model;

public class Enemy {
    private float x, y;
    private float dx, dy;
    private float attackDamage;
    private float speed;
    public Enemy () {
        this(0, 0, 30);
    }

    public Enemy (float x, float y, float attackDamage) {
        setEnemyX(x);
        setEnemyY(y);
        setAttackDamage(attackDamage);
        float angle = (float)(Math.random() * 2 * Math.PI);
        dx = (float)Math.cos(angle) * speed;
        dy = (float)Math.sin(angle) * speed;
    }

    public void enemyMove(Difficulty difficultyLevel, float screenWidth, float screenHeight) {};

    // getters
    public float getEnemyX() { return x; }
    public float getEnemyY() { return y; }
    public float getEnemyDx() { return dx; }
    public float getEnemyDy() { return dy; }
    public float getAttackDamage() { return attackDamage; }

    // setters
    public void setEnemyX(float x) { this.x = x; }
    public void setEnemyY(float y) { this.y = y; }
    public void setEnemyDx(float dx) { this.dx = dx; }
    public void setEnemyDy(float dy) { this.dy = dy; }
    public void setAttackDamage(float attackDamage) { this.attackDamage = attackDamage; }
}
