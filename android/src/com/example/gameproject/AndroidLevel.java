package com.example.gameproject;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.example.MyGame.LevelActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AndroidLevel extends AndroidApplication {
    Timer timer;
    LevelActivity la;
    ArrayList<String> paths;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        la = new LevelActivity();
        initialize(la, config);
        paths = new ArrayList<>();
        timer = new Timer();
        timer.schedule(new UpdateTimeTask1(), 0, 4);
        paths.add("try.tmx");
        paths.add("map1.tmx");
        paths.add("map2.tmx");
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
