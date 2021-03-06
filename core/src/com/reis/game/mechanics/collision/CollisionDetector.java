package com.reis.game.mechanics.collision;

import com.badlogic.gdx.math.Vector2;
import com.reis.game.entity.GameEntity;
import com.reis.game.entity.components.BodyComponent;
import com.reis.game.mechanics.TileEntityMap;
import com.reis.game.util.MapUtils;

import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.List;


/**
 * Created by bernardoreis on 11/25/16.
 */

public class CollisionDetector {

    public static CollisionResults checkCollision(GameEntity entity) {
        CollisionResults results = new CollisionResults();
        BodyComponent body = entity.getComponent(BodyComponent.class);
        if (body != null) {
            List<Vector2> hotspots = body.getHotspots();
            for (Vector2 hotspot : hotspots) {
                testCollisionsForHotspot(entity, hotspot, Vector2.Zero, results);
            }
        }
        return results;
    }

    public static CollisionResults checkCollision(GameEntity entity, Vector2 movement) {

        CollisionResults results = new CollisionResults();
        BodyComponent body = entity.getComponent(BodyComponent.class);
        if (body == null) {
            results.distanceWalked = movement;
            return results;
        }

        Vector2 distanceWalked = new Vector2(0, 0);
        Vector2 distanceToWalk = new Vector2((int) Math.abs(movement.x), (int) Math.abs(movement.y));
        Vector2 direction = new Vector2(Math.signum(movement.x), Math.signum(movement.y));

        while (distanceWalked.x < distanceToWalk.x || distanceWalked.y < distanceToWalk.y) {
            if (distanceWalked.x < distanceToWalk.x) {
                boolean collided = testCollisions(entity, distanceWalked.x, true, direction.x, results);
                if (collided)
                    distanceToWalk.x = distanceWalked.x;
                else
                    distanceWalked.x = distanceWalked.x + 1;
            }
            if (distanceWalked.y < distanceToWalk.y) {
                boolean collided = testCollisions(entity, distanceWalked.y, false, direction.y, results);
                if (collided)
                    distanceToWalk.y = distanceWalked.y;
                else
                    distanceWalked.y = distanceWalked.y + 1;
            }
        }

        results.distanceWalked = distanceWalked.scl(direction);
        return results;
    }

    private static boolean testCollisions(GameEntity entity, float distanceWalked, boolean xAxis, float sign, CollisionResults results) {
        BodyComponent component = entity.getComponent(BodyComponent.class);
        List<Vector2> hotspots = xAxis ? component.getHotspotsForXAxis(sign) : component.getHotspotsForYAxis(sign);
        Vector2 offset = getOffset(distanceWalked, xAxis, sign);

        boolean collided = false;
        for (Vector2 hotspot : hotspots) {
            collided = collided || testCollisionsForHotspot(entity, hotspot, offset, results);
        }
        return collided;
    }

    private static Vector2 getOffset(float distanceWalked, boolean xAxis, float sign) {
        Vector2 offset = new Vector2(0, 0);
        if (xAxis)
            offset.x += sign * (distanceWalked + 1);
        else
            offset.y += sign * (distanceWalked + 1);
        return offset;
    }

    private static boolean testCollisionsForHotspot(GameEntity entity, Vector2 hotspot, Vector2 offset, CollisionResults results) {
        Vector2 copy = hotspot.cpy().add(offset);
        Vector2 tile = MapUtils.getTileForPosition(copy);
        HashSet<GameEntity> entitiesInTile = TileEntityMap.getCurrentEntitiesInTile((int) tile.x, (int) tile.y);
        if (entitiesInTile == null)
            return false;

        boolean collided = false;
        for (GameEntity entityToTest : entitiesInTile) {
            if (entityToTest == entity) {
                continue;
            }
            collided = collided || testCollisionWithEntity(entity, entityToTest, offset, results);
        }
        return collided;
    }

    private static boolean testCollisionWithEntity(GameEntity entity, GameEntity entityToTest, Vector2 offset, CollisionResults results) {
        BodyComponent component = entity.getComponent(BodyComponent.class);
        if (component.shouldIgnore(entityToTest))
            return false;

        Rectangle2D intersection = testCollision(entity, entityToTest, offset);
        if (intersection == null || intersection.isEmpty())
            return false;

        results.addCollision(new Collision(entity, entityToTest, intersection));
        results.collided = true;
        BodyComponent entityBody = entityToTest.getComponent(BodyComponent.class);
        return entityBody != null && entityBody.isCollidable();
    }

    public static Rectangle2D testCollision(GameEntity entity1, GameEntity entity2) {
        return testCollision(entity1, entity2, Vector2.Zero);
    }

    public static Rectangle2D testCollision(GameEntity entity1, GameEntity entity2, Vector2 offset) {
        BodyComponent body1 = entity1.getComponent(BodyComponent.class);
        BodyComponent body2 = entity2.getComponent(BodyComponent.class);
        if (body1 != null && body2 != null && body1.isCollidable && body2.isCollidable) {
            return calculateIntersectionBetweenEntities(entity1, entity2, offset);
        } else {
            return null;
        }
    }

    private static Rectangle2D calculateIntersectionBetweenEntities(GameEntity entity1, GameEntity entity2, Vector2 offset) {
        Rectangle2D r1 = new Rectangle2D.Float(entity1.getX() + offset.x, entity1.getY() + offset.y, entity1.getWidth(), entity1.getHeight());
        Rectangle2D r2 = new Rectangle2D.Float(entity2.getX(), entity2.getY(), entity2.getWidth(), entity2.getHeight());
        return r1.createIntersection(r2);
    }

    public static boolean isTouching(GameEntity entity1, GameEntity entity2) {
        double distanceX = Math.abs(entity2.getCenterX() - entity1.getCenterX());
        double distanceY = Math.abs(entity2.getCenterY() - entity1.getCenterY());
        boolean isTouchingX = distanceX <= (entity1.getWidth() * 0.5 + entity2.getWidth() * 0.5);
        boolean isTouchingY = distanceY <= (entity1.getHeight() * 0.5 + entity2.getHeight() * 0.5);
        return isTouchingX && isTouchingY;
    }
}
