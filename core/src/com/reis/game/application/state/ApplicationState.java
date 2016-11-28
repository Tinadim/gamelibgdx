package com.reis.game.application.state;

import com.reis.game.scene.Scene;

/**
 * Created by bernardoreis on 11/16/16.
 */

public abstract class ApplicationState {

    protected Scene scene;

    public abstract void update();

    public abstract void draw();

    public abstract void dispose();

    public abstract void pause();

    public abstract void resume();

    public void resize(int width, int height) {

    }

    public void onStateStarted() {

    }

    public void onStateEnded() {

    }

}
