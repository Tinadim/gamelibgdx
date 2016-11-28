package com.reis.game.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by bernardoreis on 11/16/16.
 */

public class Scene {

    protected Stage stage;
    protected Music backgroundSong;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Music getBackgroundSong() {
        return backgroundSong;
    }

    public void setBackgroundSong(Music backgroundSong) {
        this.backgroundSong = backgroundSong;
    }

    public void update() {
        stage.act(Gdx.graphics.getDeltaTime());
    }

    public void draw() {
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }

    public void onScreenEntered() {

    }

}
