package com.example.MyGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class RocketTower extends Tower {
    ArrayList<Rocket> rockets = new ArrayList<>();
    Helicopter en = null;
    {
        this.sprite = new Sprite(new Texture("rocketTower.png"));
        s();

        this.damage = 0;
    }

    @Override
    public void attack(Enemy en){
        if(this.en == null){
            this.en = (Helicopter) en;
        }
        if (rockets.isEmpty() && en.isAlive() && Math.pow(en.enemy.getX() - this.getPosX(), 2) + Math.pow(en.enemy.getX() - this.getPosX(), 2) < Math.pow(500, 2)) {
            Rocket rocket = new Rocket();
            rockets.add(rocket);
        }
        else if(!rockets.isEmpty()){
            if(Math.pow(rockets.get(0).getSprite().getY()-en.enemy.getY(), 2) < 100 && rockets.get(0).isUp()){
                en.attacked(100);
                rockets.remove(0);
            }
            else{
                rockets.get(0).fly(this.en, this);
            }
        }
    }

    @Override
    public void attack(Enemy enemy, ShapeRenderer srend) {
    }
}
