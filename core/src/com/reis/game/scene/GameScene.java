package com.reis.game.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.SnapshotArray;
import com.reis.game.contants.GameConstants;
import com.reis.game.contants.SceneConstants;
import com.reis.game.entity.GameEntity;
import com.reis.game.entity.components.BodyComponent;
import com.reis.game.entity.player.Player;
import com.reis.game.util.EntityComparator;
import com.reis.game.util.MapUtils;
import com.reis.game.util.SceneUtils;

import java.util.List;

/**
 * Created by bernardoreis on 11/17/16.
 */

public class GameScene extends Scene implements SceneConstants {

    //References stored for easy access
    private TiledMap map;
    private CameraHandler cameraHandler;

    public GameScene() {

    }

    public void addPlayerToScene(Player player) {
        if (map == null)
            throw new IllegalStateException("Map must be parsed and stored before adding the player");
        TiledMapTileLayer layer = MapUtils.getForegroundLayer(map);
        layer.addActor(player);
    }

    @Override
    public void onScreenEntered() {
        bindOccupiedTiles();
    }

    @Override
    public void update() {
        checkInputs();
        super.update();
        sortMapEntities();
    }

    private void bindOccupiedTiles() {
        List<GameEntity> entities = SceneUtils.getEntities(this);
        for (GameEntity entity : entities) {
            BodyComponent body = entity.getComponent(BodyComponent.class);
            if (body != null) {
                body.bindTiles();
            }
        }
    }

    private void sortMapEntities() {
        TiledMapTileLayer layer = MapUtils.getForegroundLayer(map);
        SnapshotArray<Actor> children = layer.getChildren();
        children.sort(new EntityComparator());
    }

    public TiledMap getMap() {
        return map;
    }

    public CameraHandler getCameraHandler() {
        return cameraHandler;
    }

    public void storeReferences(TiledMap map, CameraHandler handler) {
        this.map = map;
        this.cameraHandler = handler;
    }

    private void checkInputs() {
        Player player = Player.getInstance();
        if (Gdx.input.isKeyPressed(Keys.SPACE)) {
            player.attack();
            return;
        }
        int directionX = Gdx.input.isKeyPressed(Keys.RIGHT) ? 1 :
                Gdx.input.isKeyPressed(Keys.LEFT) ? -1 : 0;
        int directionY = Gdx.input.isKeyPressed(Keys.UP) ? 1 :
                Gdx.input.isKeyPressed(Keys.DOWN) ? -1 : 0;
        Vector2 movement = new Vector2(directionX, directionY);
        if (!movement.equals(Vector2.Zero))
            player.move(movement.scl(GameConstants.TILE_SIZE));
        //else
            //player.idle();
        //Vector2 movement = new Vector2(directionX, directionY);
    }
}
