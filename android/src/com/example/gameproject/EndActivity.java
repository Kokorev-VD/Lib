package com.example.gameproject;

import android.content.Context;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.example.MyGame.End;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

public class EndActivity extends AndroidApplication {
    String f;
    String level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        boolean c = getIntent().getBooleanExtra("path", false);
        level = getIntent().getStringExtra("level");

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
        if (Integer.parseInt(level) > Integer.parseInt(f)) {
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.getContext().openFileOutput("xx.txt", Context.MODE_PRIVATE));

                outputStreamWriter.write(level);
                outputStreamWriter.flush();
                outputStreamWriter.close();
                System.out.println(level);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        initialize(new End(c), config);
    }
}
