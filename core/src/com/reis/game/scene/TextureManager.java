package com.reis.game.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;

/**
 * Created by bernardoreis on 9/24/17.
 */

public final class TextureManager {

    private static HashMap<String, TextureAtlas> atlasCache = new HashMap<String, TextureAtlas>();

    private TextureManager() {}

    public static TextureAtlas loadAtlas(String atlasName) {
        TextureAtlas atlas = atlasCache.get(atlasName);
        if (atlas == null) {
            atlas = loadFromAsset(atlasName);
            atlasCache.put(atlasName, atlas);
        }
        return atlas;
    }

    private static TextureAtlas loadFromAsset(String atlasName) {
        return new TextureAtlas(Gdx.files.internal("gfx/female villager/" + atlasName));
    }
}
