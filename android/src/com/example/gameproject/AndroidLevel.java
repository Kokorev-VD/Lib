package com.example.gameproject;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.example.MyGame.LevelActivity;

import java.util.Timer;
import java.util.TimerTask;

public class AndroidLevel extends AndroidApplication {
    Timer timer;
    LevelActivity la;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        la = new LevelActivity();
        initialize(la, config);
        timer = new Timer();
        timer.schedule(new UpdateTimeTask1(), 0, 4);

    }
    private class UpdateTimeTask1 extends TimerTask {
        @Override
        public void run() {
            //
        }
    }
}
