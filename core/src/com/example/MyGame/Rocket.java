package com.example.MyGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Rocket {
    private boolean once = true;
    private boolean up = false;
    private final Sprite sprite = new Sprite(new Texture("rocket1.png"));
    static float a = 1;
    {
        sprite.setSize(55, 55);
        sprite.setOriginCenter();

    }

    public void fly(Enemy enemy, Tower tower) {
        if (once) {
            once = false;
            sprite.setY(tower.getPosY());
        }
        if (!up) {
            sprite.setX(tower.getPosX());
            sprite.setY(sprite.getY() + a);
        } else {
            sprite.setX(enemy.enemy.getX());
            sprite.setY(sprite.getY() - 1.5f*a);
        }
        if (sprite.getY() > 1060) {
            up = true;
            sprite.rotate(180);
        }

    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean isUp() {
        return up;
    }
}
