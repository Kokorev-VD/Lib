package com.example.gameproject;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.example.MyGame.End;

public class EndActivity extends AndroidApplication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        boolean c = getIntent().getBooleanExtra("path",false);
        char level = getIntent().getCharExtra("level",'0');
        initialize(new End(c, level), config);
    }
}
