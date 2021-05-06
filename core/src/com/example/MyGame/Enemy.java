package com.example.MyGame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Enemy {
    public Sprite enemy;
    public Sprite blade;
    protected float health;
    private static float a = 100;
    private int indexX = 0;
    private int indexY = 0;
    private int s = 0;
    private Double velX = 2d;
    private Double velY = 2d;
    private boolean alive = true;
    int i;

    public boolean isAlive() {
        return alive;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public void Death(){
        if (health <= 0) {
            alive = false;
            enemy.setTexture(new Texture("empty_texture.png"));
            blade.setTexture(new Texture("empty_texture.png"));
            velY = 0d;
            velX = 0d;
            Rocket.a *=1.3;
            MyGame.money+=a/10;
        }
    }
    public void attacked(Tower tower){
        health-= tower.getDamage();
        this.Death();
    }
    public void attacked(int damage){
        health-=damage;
        this.Death();
    }

    public Double getVelX() {
        return velX;
    }

    public Double getVelY() {
        return velY;
    }

    public void setVelX(Double velX) {
        this.velX = velX;
    }

    public void setVelY(Double velY) {
        this.velY = velY;
    }

    public static float getA() {
        return a;
    }

    public static void setA(int a) {
        Enemy.a = a;
    }

    public int getIndexX() {
        return indexX;
    }

    public void setIndexX(int indexX) {
        this.indexX = indexX;
    }

    public int getIndexY() {
        return indexY;
    }

    public void setIndexY(int indexY) {
        this.indexY = indexY;
    }

    public abstract void move(ArrayList<Integer> routeX, ArrayList<Integer> routeY, String path, LinkedList<Enemy> enemies);
    public void drawHealth(ShapeRenderer srend){
        srend.setColor(Color.GREEN);
        srend.rect(enemy.getX(), enemy.getY()-50, health/a*75, 10);

    }
}

