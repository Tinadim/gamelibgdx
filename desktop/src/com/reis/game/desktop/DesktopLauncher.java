package com.reis.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.reis.game.Game;
import com.reis.game.audio.SoundManager;
import com.reis.game.audio.midi.DesktopMidiPlayer;
import com.reis.game.contants.GameConstants;

public class DesktopLauncher implements GameConstants {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = GAME_NAME;
		config.width = WIDTH;
		config.height = HEIGHT;
		SoundManager.setMidiPlayer(new DesktopMidiPlayer());
		new LwjglApplication(new Game(), config);
	}
}
