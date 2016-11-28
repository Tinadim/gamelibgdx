package com.reis.game.mechanics;

import com.reis.game.entity.GameEntity;
import com.reis.game.util.MapUtils;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by bernardoreis on 11/25/16.
 */

public final class TileEntityMap {

    private static HashMap<Integer, HashSet<GameEntity>> entitiesPerTile = new HashMap<Integer, HashSet<GameEntity>>();

    private TileEntityMap() {}

    public static void addEntityToTile(GameEntity entity, int col, int row) {
        HashSet<GameEntity> currentEntitiesInTile = getCurrentEntitiesInTile(col, row);
        currentEntitiesInTile.add(entity);
        entitiesPerTile.put(MapUtils.getTileId(col, row), currentEntitiesInTile);
    }

    public static void removeEntityFromTile(GameEntity entity, int col, int row) {
        int tileId = MapUtils.getTileId(col, row);
        HashSet<GameEntity> currentEntitiesInTile = getCurrentEntitiesInTile(col, row);
        if (currentEntitiesInTile == null)
            return;
        currentEntitiesInTile.remove(entity);
        if (currentEntitiesInTile.size() == 0)
            currentEntitiesInTile = null;
        entitiesPerTile.put(tileId, currentEntitiesInTile);
    }

    public static HashSet<GameEntity> getCurrentEntitiesInTile(int col, int row) {
        int tileId = MapUtils.getTileId(col, row);
        HashSet<GameEntity> currentEntitiesInTile = entitiesPerTile.get(tileId);
        if (currentEntitiesInTile == null)
            currentEntitiesInTile = new HashSet<GameEntity>();
        return currentEntitiesInTile;
    }

}
