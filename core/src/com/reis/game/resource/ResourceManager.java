package com.reis.game.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.Random;

/**
 * Created by bernardoreis on 11/19/16.
 */

public class ResourceManager {

    public static final String MUSIC_PATH = "sfx/musics/";
    public static final String SOUND_PATH = "sfx/sounds/";
    public static final String IMAGE_PATH = "gfx/";
    public static final String SCREEN_DATA_PATH = "scenes/";
    public static final String TMX_PATH = "scenes/maps/";
    public static final String FONT_PATH = "fonts/";

    public static final Random random = new Random();

    public static BitmapFont getSplashScreenFontBig() {
        return new BitmapFont(Gdx.files.internal(FONT_PATH + "SplashScreenBig.fnt"));
    }

    public static BitmapFont getSplashScreenFontSmall() {
        return new BitmapFont(Gdx.files.internal(FONT_PATH + "SplashScreenSmall.fnt"));
    }

    public static FileHandle readFile(String path) {
        return Gdx.files.internal(path);
    }

    public static FileHandle readSceneFile(String sceneName) {
        return readFile(SCREEN_DATA_PATH + sceneName);
    }

    public static Music getSong(String songName) {
        String path = MUSIC_PATH + songName;
        return Gdx.audio.newMusic(Gdx.files.internal(path));
    }
}
