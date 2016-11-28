package com.reis.game.application.state;

import com.reis.game.scene.SceneManager;

/**
 * Created by bernardoreis on 11/16/16.
 */

public class InitialState extends ApplicationState {

    @Override
    public void onStateStarted() {
        this.scene = SceneManager.createSplashScreen();
        //SoundManager.playSong(scene.getBackgroundSong());
    }

    @Override
    public void update() {
        this.scene.update();
    }

    @Override
    public void draw() {
        this.scene.draw();
    }

    @Override
    public void dispose() {
        this.scene.dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
