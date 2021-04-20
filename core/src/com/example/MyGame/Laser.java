package com.example.MyGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Laser extends Tower{

    {
        this.sprite = new Sprite(new Texture("laser.png"));
        s();
    }

    @Override
    public void attack(Enemy enemy) { }

    @Override
    public void attack(Enemy en, ShapeRenderer srend) {
        if (en instanceof Tank && en.isAlive() & Math.pow(en.enemy.getX() - this.getPosX(), 2) + Math.pow(en.enemy.getX() - this.getPosX(), 2) < Math.pow(300, 2)) {
            try {
                en.attacked(this);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            srend.rectLine(en.enemy.getX() + 25, en.enemy.getY() + 30, this.getPosX() + 35, this.getPosY() + 35, 3f);
        }
    }
}
