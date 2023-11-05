package com.example.dungencrawler.model;

public class Enemy {
    private float x, y;
    private float attackDamage;

    public Enemy () {
        this(0, 0, 30);
    }

    public Enemy (float x, float y, float attackDamage) {
        setEnemyX(x);
        setEnemyY(y);
        setAttackDamage(attackDamage);
    }

    // getters
    public float getEnemyX() { return x; }
    public float getEnemyY() { return y; }
    public float getAttackDamage() { return attackDamage; }

    // setters
    public void setEnemyX(float x) { this.x = x; }
    public void setEnemyY(float y) { this.y = y; }
    public void setAttackDamage(float attackDamage) { this.attackDamage = attackDamage; }
}
