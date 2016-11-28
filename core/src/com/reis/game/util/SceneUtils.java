package com.reis.game.util;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.SnapshotArray;
import com.reis.game.entity.GameEntity;
import com.reis.game.scene.GameScene;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bernardoreis on 11/20/16.
 */

public final class SceneUtils {

    private SceneUtils() {}

    public static void centerLabelHorizontally(Stage stage, Label label) {
        float centerX = stage.getWidth() * .5f - label.getPrefWidth() * .5f;
        label.setX(centerX);
    }

    public static List<GameEntity> getEntities(GameScene scene) {
        TiledMapTileLayer layer = MapUtils.getForegroundLayer(scene.getMap());
        SnapshotArray<Actor> children = layer.getChildren();
        List<GameEntity> entities = new ArrayList<GameEntity>();
        for (Actor actor : children) {
            if (actor instanceof GameEntity)
                entities.add((GameEntity) actor);
        }
        return entities;
    }
}
