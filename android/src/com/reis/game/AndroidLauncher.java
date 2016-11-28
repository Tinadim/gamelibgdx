package com.reis.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.reis.game.audio.AndroidMidiPlayer;
import com.reis.game.audio.SoundManager;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		SoundManager.setMidiPlayer(new AndroidMidiPlayer(this));
		initialize(new Game(), config);
	}
}
