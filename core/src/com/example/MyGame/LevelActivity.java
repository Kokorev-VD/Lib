package com.example.MyGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelActivity extends ApplicationAdapter {

    Batch batch;
    Texture bg;
    BitmapFont font;
    int row = 200;
    int high = 100;
    int level = 1;
    @Override
    public void create() {

        bg = new Texture("bge.png");
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(3);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.setColor(Color.BLACK);
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        for(int x = 0; x<5; x++){
            for(int y = 0; y<4; y++){
                font.draw(batch, "" + level, row * x,high*y);
                level++;
            }
        }
        batch.end();
    }

    @Override
    public void dispose() {
        font.dispose();
        batch.dispose();
    }
}
