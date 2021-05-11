package com.example.gameproject;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.example.MyGame.MyGame;

import java.util.Timer;
import java.util.TimerTask;

public class AndroidGame extends AndroidApplication {
Timer timer;
MyGame mg;
String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        path = getIntent().getStringExtra("path");
        mg = new MyGame(path);
        initialize(mg, config);
        timer = new Timer();
        timer.schedule(new UpdateTimeTask1(), 0, 4);
    }

    private class UpdateTimeTask1 extends TimerTask {
        @Override
        public void run() {
            if((mg.wave == 2 && (path.equals("map0.tmx") || path.equals("map1.tmx"))) || (mg.wave == 6 && (path.equals("map2.tmx") || path.equals("map3.tmx"))) && !mg.lost){
                Intent intent = new Intent(AndroidGame.this, EndActivity.class);
                intent.putExtra("path", true);
                startActivity(intent);
                System.exit(1);
                timer.cancel();
            }else if(mg.lost){
                Intent intent = new Intent(AndroidGame.this, EndActivity.class);
                intent.putExtra("path", false);
                startActivity(intent);
                System.exit(1);
                timer.cancel();
            }
        }
    }
}
