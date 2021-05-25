package com.example.gameproject;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.example.MyGame.End;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EndActivity extends AndroidApplication {
    String f;
    String level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        boolean c = getIntent().getBooleanExtra("path", false);
        level = getIntent().getStringExtra("level");
        initialize(new End(c), config);
        try {
            File f1 = this.getContext().getFileStreamPath("xx.txt");
            FileReader fr = new FileReader(f1);
            f = String.valueOf((char)fr.read());
            System.out.println(f);
            System.out.println("-------------------");
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Integer.parseInt(level) > Integer.parseInt(f)) {
            try {
                File f = this.getContext().getFileStreamPath("xx.txt");
                FileWriter fw = new FileWriter(f, false);
                FileReader fr = new FileReader(f);

                fw.write(level);
                fw.flush();
                fw.close();

                System.out.println((char)fr.read());
                System.out.println(level);
                System.out.println("-------------------");
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
