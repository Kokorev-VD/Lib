package com.example.gameproject;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.example.MyGame.LevelActivity;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AndroidLevel extends AndroidApplication {
    Timer timer;
    LevelActivity la;
    ArrayList<String> paths;
    String f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        try {
            File f1 = this.getContext().getFileStreamPath("xx.txt");
            FileReader fr = new FileReader(f1);
            f = String.valueOf((char) fr.read());
            System.out.println(f);
            System.out.println("-------------------");
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        la = new LevelActivity(f);
        initialize(la, config);

        paths = new ArrayList<>();
        timer = new Timer();
        timer.schedule(new UpdateTimeTask1(), 0, 4);
        paths.add("map0.tmx");
        paths.add("map1.tmx");
        paths.add("map2.tmx");
        paths.add("map3.tmx");


    }

    private class UpdateTimeTask1 extends TimerTask {
        @Override
        public void run() {
            if (la.isPressed()) {
                Intent intent = new Intent(AndroidLevel.this, AndroidGame.class);
                intent.putExtra("path", paths.get(la.getLast().getLevel() - 1));

                startActivity(intent);
                timer.cancel();
            }
        }
    }
}
