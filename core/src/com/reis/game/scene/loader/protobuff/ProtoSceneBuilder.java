package com.reis.game.scene.loader.protobuff;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.reis.game.entity.GameEntity;
import com.reis.game.resource.ResourceManager;
import com.reis.game.resource.prototype.EntityTypeProto.EntityData;
import com.reis.game.resource.prototype.ScreenProto;
import com.reis.game.resource.prototype.ScreenProto.ScreenData;
import com.reis.game.scene.CameraHandler;
import com.reis.game.scene.GameScene;
import com.reis.game.scene.SceneManager;
import com.reis.game.scene.loader.SceneLoader;
import com.reis.game.util.MapUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by bernardoreis on 11/16/16.
 */

public class ProtoSceneBuilder extends SceneLoader {

    private ProtoEntityBuilder entityBuilder = new ProtoEntityBuilder();

    @Override
    public GameScene loadScene(String sceneName) {
        FileHandle handle = ResourceManager.readSceneFile(sceneName);
        InputStream stream = handle.read();
        try {
            ScreenData screenData = ScreenProto.ScreenData.parseFrom(stream);
            return parseScreenData(screenData);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private GameScene parseScreenData(ScreenData screenData) {
        GameScene scene = new GameScene();
        SceneManager.setTempScene(scene);
        scene.setBackgroundSong(parseSceneMusic(screenData));
        scene.setStage(createStage(scene, screenData));
        return scene;
    }

    private Stage createStage(GameScene scene, ScreenData screenData) {
        TiledMap map = new TmxMapLoader().load(ResourceManager.TMX_PATH + screenData.getTileMapName());
        List<EntityData> entityDataList = screenData.getEntityDataList();
        addEntitiesToMap(entityDataList, map);
        map.setMapRenderer(new OrthogonalTiledMapRenderer(map));
        CameraHandler cameraHandler = new CameraHandler();
        Stage stage = new Stage();
        stage.addActor(map);
        stage.addActor(cameraHandler);
        scene.storeReferences(map, cameraHandler);
        return stage;
    }

    private void addEntitiesToMap(List<EntityData> entityDataList, TiledMap map) {
        TiledMapTileLayer foregroundLayer = MapUtils.getForegroundLayer(map);
        for(EntityData entityData : entityDataList) {
            GameEntity entity = entityBuilder.buildEntity(entityData);
            foregroundLayer.addActor(entity);
        }
    }

    private Music parseSceneMusic(ScreenData screenData) {
        return null;
    }
}
