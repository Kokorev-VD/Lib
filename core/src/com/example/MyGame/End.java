package com.example.MyGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class End extends ApplicationAdapter {

    Batch batch;
    Texture bg;
    BitmapFont font;
    ArrayList<MyButton> btns;

    boolean completed;
    public End(boolean c){
        completed = c;
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

    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if(completed){
            font.draw(batch, "Victory", 1000f, 300f);
        }
        else{
            font.draw(batch, "Lose", 1000f, 300f);
        }
        font.draw(batch, "Exit", 1000f, 100f);
        batch.end();
    }

    @Override
    public void dispose() {
        font.dispose();
        batch.dispose();
    }
}
