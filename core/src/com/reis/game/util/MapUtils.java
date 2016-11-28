package com.reis.game.util;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.reis.game.contants.GameConstants;
import com.reis.game.scene.SceneManager;

/**
 * Created by bernardoreis on 11/20/16.
 */

public final class MapUtils {

    public static final String FOREGROUND_LAYER_NAME = "Foreground";

    private MapUtils() {

    }

    public static TiledMapTileLayer getForegroundLayer(TiledMap map) {
        return (TiledMapTileLayer) map.getLayers().get(FOREGROUND_LAYER_NAME);
    }

    public static Vector2 getCoordsForTile(Vector2 tile) {
        return new Vector2(toCoord(tile.x), toCoord(tile.y));
    }

    public static Vector2 getCoordsForTile(int row, int col) {
        return new Vector2(toCoord(row), toCoord(col));
    }

    public static Vector2 getTileForPosition(Vector2 position) {
        int row = toTileCoord(position.y);
        int col = toTileCoord(position.x);
        return new Vector2(col, row);
    }

    public static int toTileCoord(float coord) {
        return (int) coord / GameConstants.TILE_SIZE;
    }

    public static float toCoord(int tileCoord) {
        return tileCoord * GameConstants.TILE_SIZE;
    }

    public static float toCoord(float tileCoord) {
        return tileCoord * GameConstants.TILE_SIZE;
    }

    public static int getTileId(int col, int row) {
        Map map = SceneManager.getCurrentScene().getMap();
        int tileWidth = map.getProperties().get("width", Integer.class);
        return row * tileWidth + col;
    }
}
