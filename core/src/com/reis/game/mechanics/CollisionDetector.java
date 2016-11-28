package com.reis.game.mechanics;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.reis.game.entity.GameEntity;
import com.reis.game.entity.components.CollisionComponent;
import com.reis.game.util.MapUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by bernardoreis on 11/25/16.
 */

public class CollisionDetector {

    public static CollisionResults checkCollision(GameEntity entity, Vector2 movement) {

        int stepsWalkedX = 0, stepsToWalkX = (int) Math.abs(movement.x);
        int stepsWalkedY = 0, stepsToWalkY = (int) Math.abs(movement.y);
        int signX = (int) Math.signum(movement.x);
        int signY = (int) Math.signum(movement.y);

        List<Vector2> hotspots = entity.getHotspots();
        List<Vector2> hotspotsToTestX = getHotspotsToTest(entity, hotspots, true, signX);
        List<Vector2> hotspotsToTestY = getHotspotsToTest(entity, hotspots, false, signY);

        CollisionResults results = new CollisionResults();

        while(stepsWalkedX < stepsToWalkX || stepsWalkedY < stepsToWalkY) {
            if (stepsWalkedX < stepsToWalkX) {
                boolean walked = walk(true, signX, hotspotsToTestX, results);
                if (!walked)
                    stepsToWalkX = stepsWalkedX;
                else
                    stepsWalkedX++;
            }
            if (stepsWalkedY < stepsToWalkY) {
                boolean walked = walk(false, signY, hotspotsToTestY, results);
                if (!walked)
                    stepsToWalkY = stepsWalkedY;
                else
                    stepsWalkedY++;
            }
        }
        results.stepsWalkedX = stepsWalkedX * signX;
        results.stepsWalkedY = stepsWalkedY * signY;
        return results;
    }

    private static List<Vector2> getHotspotsToTest(GameEntity entity, List<Vector2> hotspots, boolean xAxis, int sign) {
        int minHotspotIndex, maxHotspotIndex;
        if (xAxis) {
            minHotspotIndex = (sign > 0) ? entity.getTileWidth() : 2 * entity.getTileWidth() + entity.getTileHeight();
            maxHotspotIndex = (sign > 0) ? entity.getTileWidth() + entity.getTileHeight() : hotspots.size();
        } else {
            minHotspotIndex = (sign > 0) ? entity.getTileWidth() + entity.getTileHeight() : 0;
            maxHotspotIndex = (sign > 0) ? 2 * entity.getTileWidth() + entity.getTileHeight() : entity.getTileWidth();
        }

        List<Vector2> hotspotsToTest = new ArrayList<Vector2>(maxHotspotIndex - minHotspotIndex);
        for (int i = minHotspotIndex; i <= maxHotspotIndex; i++) {
            Vector2 hotspot = hotspots.get(i % hotspots.size());
            hotspotsToTest.add(new Vector2(hotspot));
        }
        return hotspotsToTest;
    }

    private static boolean walk(boolean xAxis, int sign, List<Vector2> hotspots, CollisionResults results) {
        boolean walked = true;
        for (Vector2 hotspot : hotspots) {
            if (xAxis)
                hotspot.x += sign;
            else
                hotspot.y += sign;
            walked = walked && testCollisionsForHotspot(hotspot, results);
        }
        return walked;
    }

    private static boolean testCollisionsForHotspot(Vector2 hotspot, CollisionResults results) {
        Vector2 tile = MapUtils.getTileForPosition(hotspot);
        HashSet<GameEntity> entitiesInTile = TileEntityMap.getCurrentEntitiesInTile((int) tile.x, (int) tile.y);
        if (entitiesInTile == null)
            return true;
        boolean walked = true;
        for (GameEntity entity : entitiesInTile) {
            walked = walked && testCollisionWithEntity(hotspot, entity, results);
        }
        return walked;
    }

    private static boolean testCollisionWithEntity(Vector2 hotspot, GameEntity entity, CollisionResults results) {
        CollisionComponent component = entity.getComponent(CollisionComponent.class);
        if (component == null || !checkCollisionWithHotspot(entity, hotspot))
            return true;
        results.addCollision(entity);
        return component.isTrigger;
    }

    private static boolean checkCollisionWithHotspot(GameEntity entity, Vector2 point) {
        Rectangle rect = new Rectangle(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
        return rect.contains(point);
    }

}
