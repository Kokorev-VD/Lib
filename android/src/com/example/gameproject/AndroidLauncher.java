package com.example.gameproject;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.example.MyGame.MyStartActivity;

import java.util.Timer;
import java.util.TimerTask;

public class AndroidLauncher extends AndroidApplication {

	MyStartActivity ma;
	Timer timer;
	boolean pressed = false;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		ma = new MyStartActivity();
		initialize(ma, config);
		timer = new Timer();
		timer.schedule(new UpdateTimeTask1(), 0, 4);

	}

	private class UpdateTimeTask1 extends TimerTask {
		@Override
		public void run() {
			if(ma.pressed){
				pressed = true;

			}
			if(pressed){
				Intent intent = new Intent(AndroidLauncher.this, AndroidLevel.class);
				startActivity(intent);
timer.cancel();
			}
		}
	}
}
