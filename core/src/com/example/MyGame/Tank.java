package com.example.MyGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.Objects;

public class Tank extends Enemy {
    boolean was = false;

    public Tank(int x, int y) {
        this.enemy = new Sprite(new Texture("tank.png"));
        this.enemy.setSize(50f, 50f);
        super.blade = new Sprite(new Texture("empty_texture.png"));
        this.enemy.setX(x);
        this.enemy.setY(y);
    }

    @SuppressWarnings("NewApi")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tank tank = (Tank) o;
        return Objects.equals(enemy, tank.enemy);
    }

    @SuppressWarnings("NewApi")
    @Override
    public int hashCode() {
        return Objects.hash(enemy);
    }
    private void rotation1() {
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
                    enemy.rotate(-2f);
                    super.setS(super.getS() - 2);
                } else {
                    super.setS(0);
                    super.setVelX(2d);
                    super.setVelY(2d);
                    was = false;
                }
                break;
        }
    }

    private void rotation2() {
        switch (super.getIndexX()) {
            case 3:
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
            case 1:
            case 2:
            case 6:
                if (super.getS() > -90) {
                    enemy.setOriginCenter();
                    enemy.rotate(-2f);
                    super.setS(super.getS() - 2);
                } else {
                    super.setS(0);
                    super.setVelX(2d);
                    super.setVelY(2d);
                    was = false;
                }
                break;
        }
    }
    @Override
    public void move(ArrayList<Integer> routeX, ArrayList<Integer> routeY, String path){
        if (was) {
            super.setVelX((double) 0);
            super.setVelY((double) 0);
            if (path.equals("try.tmx")) {
                rotation1();
            } else if (path.equals("map1.tmx")) {
                rotation2();
            }
            else {
                super.setVelX(2d);
                super.setVelY(2d);
            }
        }

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
