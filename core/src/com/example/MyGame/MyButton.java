package com.example.MyGame;

public class MyButton {
    private final int x;
    private final int y;
    private int level;

    public int getX() {
        return x;
    }

    public int getLevel() {
        return level;
    }

    public int getY() {
        return y;
    }


    public MyButton(int x, int y, int level){
        this.x = x;
        this.y = y;
        this.level = level;
    }
}
