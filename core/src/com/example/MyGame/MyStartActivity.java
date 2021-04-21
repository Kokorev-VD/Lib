package com.example.MyGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MyStartActivity extends ApplicationAdapter {
    SpriteBatch batch;
    Texture bg;
    Sprite btn;
    Sprite exit;
    public boolean pressed = false;
    @Override
    public void create() {

        bg = new Texture("bge.png");
        batch = new SpriteBatch();
        btn = new Sprite((new Texture("btn.png")));
        btn.setSize(300, 150);
        btn.setX(920f);
        btn.setY(600f);
        exit = new Sprite((new Texture("exit.png")));
        exit.setSize(300, 150);
        exit.setX(920f);
        exit.setY(400f);
        Gdx.input.setInputProcessor(new InputAdapter() {
            public boolean touchUp(int x, int y, int pointer, int button) {
                if(x>=750 && x<=1200 && 1100-y>=600 && 1100-y<=750){
                    pressed = true;
                    System.out.println(x);
                    System.out.println(y);
                }
                if(x>=750 && x<=1200 && 1100-y>=400 && 1100-y<=550){
                    System.exit(1);
                }

                return true;
            }
        });
    }
    @Override
    public void render() {
        batch.begin();
        batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        exit.draw(batch);
        btn.draw(batch);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();

    }
}
