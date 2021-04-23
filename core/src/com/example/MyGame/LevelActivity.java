package com.example.MyGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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

    int level = 1;
    @Override
    public void create() {
        btns = new ArrayList<>();
        bg = new Texture("bge.png");
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(4);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.setColor(Color.BLACK);
        for(int y = 3; y>0; y--){
            for(int x = 1; x<5; x++){
                btns.add(new MyButton(400 * x, 250*y+25, level));
                level++;
            }
        }

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
