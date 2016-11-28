package com.reis.game.scene.loader;

import com.reis.game.entity.player.Player;
import com.reis.game.scene.GameScene;
import com.reis.game.scene.loader.protobuff.ProtoSceneBuilder;
import com.reis.game.state.GameState;

/**
 * Created by bernardoreis on 11/16/16.
 */

public abstract class SceneLoader {

    private static SceneLoader loader = new ProtoSceneBuilder();

    protected abstract GameScene loadScene(String sceneName);

    public static GameScene loadFromState(GameState state) {
        return load(state.mapName);
    }

    public static GameScene load(String mapName) {
        GameScene scene = loader.loadScene(mapName);
        scene.getCameraHandler().setEntityToFollow(Player.getInstance());
        return scene;
    }

    public static void setLoader(SceneLoader loader) {
        SceneLoader.loader = loader;
    }

}
