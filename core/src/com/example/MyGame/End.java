package com.example.MyGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class End extends ApplicationAdapter {

    Batch batch;
    Texture bg;
    BitmapFont font;
    ArrayList<MyButton> btns;
    File mFile;
    char f;
    FileReader reader;
    char l;
    boolean completed;
    public End(boolean c, char level){
        completed = c;
        l = level;
    }

    @Override
    public void create() {
        mFile = new File("Level.txt");
        btns = new ArrayList<>();
        try {
            reader = new FileReader("notes3.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bg = new Texture("bge.png");
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(4);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.setColor(Color.BLACK);
        Gdx.input.setInputProcessor(new InputAdapter() {
            public boolean touchUp(int x, int y, int pointer, int button) {
                if (x > 950 && x < 1050 && 1100-y > 50 && 1100-y < 150) {
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
        if(completed){
            try
            {
                int c;
                while((c=reader.read())!=-1){
                    f = (char) c;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            font.draw(batch, "Victory", 970f, 600f);
        }
        else{
            font.draw(batch, "Lose", 1000f, 600f);
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
