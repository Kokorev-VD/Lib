package com.example.MyGame;

import java.util.LinkedList;

public class Bombing implements Ability{
    @Override
    public void ability(LinkedList<Enemy> enemies) {
        for(Enemy en:enemies){
            en.attacked((int) (Enemy.getA()/10));
        }
    }
}
