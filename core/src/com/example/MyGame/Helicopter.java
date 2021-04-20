package com.example.MyGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.Objects;

public class Helicopter extends Enemy {

    private  boolean was = false;
    public Helicopter(int x, int y) {
        this.blade = new Sprite(new Texture("blades.png"));
        this.enemy = new Sprite(new Texture("helicopter.png"));
        this.blade.setX(x);
        this.blade.setY(y);
        this.blade.setSize(40f, 40f);
        this.enemy.setX(x);
        this.enemy.setY(y);
        this.enemy.setSize(75f, 35f);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Helicopter that = (Helicopter) o;
        return was == that.was &&
                Objects.equals(blade, that.blade) &&
                Objects.equals(enemy, that.enemy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blade, enemy, was);
    }

    @Override
    public void move(ArrayList<Integer> routeX, ArrayList<Integer> routeY) {
        if (was) {
            super.setVelX((double) 0);
            super.setVelY((double) 0);
            switch (super.getIndexX()) {
                case 1:
                case 4:
                case 5:
                    if (super.getS() < 90) {
                        enemy.setOriginCenter();
                        enemy.rotate(2f);
                        super.setS(super.getS() + 2);
                    } else {
                        super.setS(0);
                        super.setVelX(2d);
                        super.setVelY(2d);
                        was = false;
                    }
                    break;
                case 2:
                case 3:
                case 6:
                    if (super.getS() > -90) {
                        enemy.setOriginCenter();
                        enemy.rotate( -2f);
                        super.setS((int) (super.getS()-2));
                    } else {
                        super.setS(0);
                        super.setVelX(2d);
                        super.setVelY(2d);
                        was = false;
                    }
                    break;
            }
        } else {
            super.setVelX(2d);
            super.setVelY(2d);
        }
        blade.setX(enemy.getX()+17);
        blade.setY(enemy.getY()-2);
        blade.setOriginCenter();
        blade.rotate(7);
        if ((int) enemy.getX() > routeX.get(super.getIndexX())) {
            enemy.setX((float) (enemy.getX() - super.getVelX()));
        } else if ((int) enemy.getX() < routeX.get(super.getIndexX())) {
            enemy.setX((float) (enemy.getX() + super.getVelX()));
        }
        if ((int) enemy.getY() > routeY.get(super.getIndexY())) {
            enemy.setY((float) (enemy.getY() - super.getVelY()));

        } else if ((int) enemy.getY() < routeY.get(super.getIndexY())) {
            enemy.setY((float) (enemy.getY() + super.getVelY()));
        }
        if ((int) enemy.getX() == routeX.get(super.getIndexX()) && (int) enemy.getY() == routeY.get(super.getIndexY())) {
            super.setIndexX(super.getIndexX() + 1);
            super.setIndexY(super.getIndexY() + 1);
            was = true;
        }

    }
}
