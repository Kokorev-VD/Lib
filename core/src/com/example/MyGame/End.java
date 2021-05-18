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
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class End extends ApplicationAdapter {

    Batch batch;
    Texture bg;
    BitmapFont font;
    ArrayList<MyButton> btns;
    File mFile;
    char f;
    FileReader reader;
    FileWriter writer;
    char l;
    boolean completed;
    public End(boolean c, char level){
        completed = c;
        l = level;
    }

    @Override
    public void create() {
        mFile = new File("l1.txt");
        btns = new ArrayList<>();
        /*try {
            reader = new FileReader("notes3.txt");

            int c;
            while ((c = reader.read()) != -1) {
                f = (char) c;
            }
        } catch (IOException e) {
           e.printStackTrace();
       }
        try {
            writer = new FileWriter("notes3.txt", false);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
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
            /*if((int) f < (int) l){
                try{
                    writer.append(l);

                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
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
