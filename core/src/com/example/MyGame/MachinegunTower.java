package com.example.MyGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MachinegunTower extends Tower{
    {
        this.sprite = new Sprite(new Texture("Machinegun.png"));
        s();
        
    }
    @Override
    public void attack(Enemy enemy) throws Throwable {

    }

    @Override
    public void attack(Enemy enemy, ShapeRenderer srend) {

    }
}
