package com.example.MyGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class LevelActivity extends ApplicationAdapter {

    Batch batch;
    Texture bg;
    BitmapFont font;
    ArrayList<MyButton> btns;
    String f;
    public boolean isPressed() {
        return pressed;
    }

    public MyButton getLast() {
        return last;
    }

    boolean pressed = false;
    MyButton last;
    int level = 1;

    public LevelActivity(String f) {
        this.f = f;
    }

    @Override
    public void create() {
        btns = new ArrayList<>();
        bg = new Texture("bge.png");

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(4);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.setColor(Color.BLACK);
        for (int x = 1; x < 5; x++) {
            btns.add(new MyButton(400 * x, 525, level));
            level++;
        }
        Gdx.input.setInputProcessor(new InputAdapter() {
            public boolean touchUp(int x, int y, int pointer, int button) {
                for (MyButton btn : btns) {
                    if (btn.getX() + 200 > x && btn.getX() - 200 < x && btn.getY() + 125 > 1100 - y && btn.getY() - 125 < 1100 - y && btn.getLevel() <= Integer.parseInt(f) + 1) {
                        pressed = true;
                        last = btn;
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        for(MyButton btn:btns){
           font.draw(batch, "Level " + btn.getLevel(), btn.getX(), btn.getY());
        }
        batch.end();
    }

    @Override
    public void dispose() {
        font.dispose();
        batch.dispose();
    }
}
