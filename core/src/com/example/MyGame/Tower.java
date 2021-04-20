package com.example.MyGame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.LinkedList;

abstract class Tower {
    int damage = 1;
    int posX;
    int posY;
    double health = 75;
    Sprite sprite;

    public int getDamage() {
        return damage;
    }

    public void s() {
        sprite.setSize(75, 75);
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
        sprite.setX(posX);
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
        sprite.setY(posY);
    }

    abstract public void attack(Enemy enemy) throws Throwable;
    abstract public void attack(Enemy enemy, ShapeRenderer srend);
    public void drawHealth(ShapeRenderer srend, LinkedList<Tower> twrs){
        if(!this.equals(twrs.getLast())) {
            if (health <= 0) {
                twrs.remove(this);
            }
            srend.setColor(Color.GREEN);
            srend.rect(posX, posY - 50, (float) health, 10);
            srend.setColor(Color.RED);
            health -= 0.25;
        }
    }
}