package com.reis.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.reis.game.application.GameManager;
import com.reis.game.application.state.ApplicationState;

public class Game implements ApplicationListener {

	public final FPSLogger logger = new FPSLogger();

	@Override
	public void create () {
		GameManager.initApplication();
	}

	@Override
	public void resize(int width, int height) {
		GameManager.getApplicationState().resize(width, height);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		ApplicationState state = GameManager.getApplicationState();
		state.update();
		state.draw();
		//logger.log();
	}

	@Override
	public void pause() {
		GameManager.getApplicationState().pause();
	}

	@Override
	public void resume() {
		GameManager.getApplicationState().resume();
	}

	@Override
	public void dispose () {
		GameManager.getApplicationState().dispose();
	}
}
