package com.reis.game.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

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
    public static final String SKIN_PATH = "data/skins/";

    public static final Random random = new Random();
    private static Skin dialogSkin = buildDialogSkin();

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

    public static Skin getDialogSkin() {
        return dialogSkin;
        //return new Skin(Gdx.files.internal(SKIN_PATH + "default/uiskin.json"));
    }

    public static Texture getDialogBackground() {
        return new Texture(Gdx.files.internal(IMAGE_PATH + "dialogBackground.png"));
    }

    private static Skin buildDialogSkin() {
        Skin dialogSkin = new Skin();
        NinePatch patch = new NinePatch(getDialogBackground(), 8, 8, 8, 8);
        patch.setPadding(0, 0, 0, 0);
        dialogSkin.add("background", patch);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/roboto.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        BitmapFont font12 = generator.generateFont(parameter);
        generator.dispose();

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.fontColor = Color.BLACK;
        labelStyle.font = font12;

        dialogSkin.add("default", labelStyle);
        return dialogSkin;
    }
}
