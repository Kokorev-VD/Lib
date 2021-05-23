package com.example.gameproject;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.example.MyGame.LevelActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AndroidLevel extends AndroidApplication {
    Timer timer;
    LevelActivity la;
    ArrayList<String> paths;
    String f;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        try {
            InputStream is = getAssets().open("xx.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
           f = new String(buffer);
            System.out.println(f);
        } catch (IOException e) {
            throw new RuntimeException(e);
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

            if(la.isPressed()){
                Intent intent = new Intent(AndroidLevel.this, AndroidGame.class);
                intent.putExtra("path", paths.get(la.getLast().getLevel()-1));

                startActivity(intent);
timer.cancel();
            }
        }
    }
}
