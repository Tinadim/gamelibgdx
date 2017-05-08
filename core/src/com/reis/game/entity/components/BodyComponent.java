package com.reis.game.entity.components;

import com.badlogic.gdx.math.Vector2;
import com.reis.game.entity.GameEntity;
import com.reis.game.mechanics.TileEntityMap;
import com.reis.game.mechanics.collision.Collision;
import com.reis.game.mechanics.collision.CollisionDetector;
import com.reis.game.mechanics.collision.CollisionListener;
import com.reis.game.mechanics.collision.CollisionResults;
import com.reis.game.scene.SceneManager;
import com.reis.game.util.MapUtils;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.reis.game.contants.GameConstants.TILE_SIZE;

/**
 * Created by bernardoreis on 11/25/16.
 */

public class BodyComponent extends EntityComponent {

    public enum CollisionCheckType {
        ACTIVE, PASSIVE
    }

    public boolean isTrigger = false;
    public boolean isCollidable = true;

    public CollisionCheckType collisionCheckype = CollisionCheckType.ACTIVE;
    public HashSet<Class<? extends GameEntity>> entitiesToIgnore;
    public List<CollisionListener> listeners;

    private List<Vector2> hotspots;

    public BodyComponent(GameEntity entity) {
        super(entity);
    }

    public void positionChanged() {
        invalidateHotspots();
        if (SceneManager.getCurrentScene() != null) {
            bindTiles();
        }
    }

    public List<Vector2> getHotspots() {
        if (hotspots != null)
            return hotspots;

        int tileWidth = entity.getTileWidth();
        int tileHeight = entity.getTileHeight();

        hotspots = new ArrayList<Vector2>(2 * (tileWidth + tileHeight));
        float x = entity.getX();
        float y = entity.getY();

        for(int i = 0; i <= tileWidth; i++) {
            x += i * TILE_SIZE;
            if (i == tileWidth)
                x--;
            hotspots.add(new Vector2(x, y));
        }
        for(int i = 1; i <= tileHeight; i++) {
            y += i * TILE_SIZE;
            if (i == tileHeight)
                y--;
            hotspots.add(new Vector2(x, y));
        }
        for(int i = 1; i <= tileWidth; i++) {
            x -= i * TILE_SIZE;
            if (i == tileWidth)
                x++;
            hotspots.add(new Vector2(x, y));
        }
        for(int i = 1; i < tileHeight; i++) {
            y -= i * TILE_SIZE;
            if (i == tileHeight)
                y++;
            hotspots.add(new Vector2(x, y));
        }
        return hotspots;
    }

    public void invalidateHotspots() {
        this.hotspots = null;
    }

    public void bindTiles() {
        List<Vector2> hotspots = getHotspots();
        for (Vector2 spot : hotspots) {
            int row = MapUtils.toTileCoord(spot.y);
            int col = MapUtils.toTileCoord(spot.x);
            TileEntityMap.addEntityToTile(entity, col, row);
        }
    }

    public void unbindTiles() {
        List<Vector2> hotspots = getHotspots();
        for (Vector2 spot : hotspots) {
            int row = MapUtils.toTileCoord(spot.y);
            int col = MapUtils.toTileCoord(spot.x);
            TileEntityMap.removeEntityFromTile(entity, col, row);
        }
    }

    public List<Vector2> getHotspotsForXAxis(float sign) {
        int minIndex = (sign > 0) ? entity.getTileWidth() : 2 * entity.getTileWidth() + entity.getTileHeight();
        int maxIndex = (sign > 0) ? entity.getTileWidth() + entity.getTileHeight() : hotspots.size();
        return getHotspotsInRange(minIndex, maxIndex);
    }

    public List<Vector2> getHotspotsForYAxis(float sign) {
        int minIndex = (sign > 0) ? entity.getTileWidth() + entity.getTileHeight() : 0;
        int maxIndex = (sign > 0) ? 2 * entity.getTileWidth() + entity.getTileHeight() : entity.getTileWidth();
        return getHotspotsInRange(minIndex, maxIndex);
    }

    private List<Vector2> getHotspotsInRange(int minIndex, int maxIndex) {
        List<Vector2> hotspotsToTest = new ArrayList<Vector2>(maxIndex - minIndex);
        for (int i = minIndex; i <= maxIndex; i++) {
            Vector2 hotspot = hotspots.get(i % hotspots.size());
            hotspotsToTest.add(new Vector2(hotspot));
        }
        return hotspotsToTest;
    }

    public CollisionResults checkCollisionForMovement(Vector2 movement) {
        CollisionResults results = CollisionDetector.checkCollision(this.entity, movement);
        this.resolveCollisions(results);
        return results;
    }

    public Rectangle2D checkIntersectionWithEntity(GameEntity entity) {
        return checkIntersectionWithEntity(entity, Vector2.Zero);
    }

    public Rectangle2D checkIntersectionWithEntity(GameEntity entity, Vector2 offset) {
        BodyComponent body = entity.getComponent(BodyComponent.class);
        if (body != null && body.isCollidable) {
            return calculateIntersectionWithEntity(entity, offset);
        } else {
            return null;
        }
    }

    private Rectangle2D calculateIntersectionWithEntity(GameEntity entity, Vector2 offset) {
        Rectangle2D r1 = new Rectangle2D.Float(this.entity.getX() + offset.x, this.entity.getY() + offset.y, this.entity.getWidth(), this.entity.getHeight());
        Rectangle2D r2 = new Rectangle2D.Float(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
        return r1.createIntersection(r2);
    }

    public void setIgnoreSameType(boolean ignore) {
        if (ignore) {
            addEntityToIgnore(this.entity.getClass());
        } else {
            removeEntityFromIgnoredList(this.entity.getClass());
        }
    }

    private void resolveCollisions(CollisionResults results) {
        if (results.collisions != null && results.collisions.size() > 0) {
            for (Collision collision : results.collisions) {
                notifyListeners(collision);
            }
        }
    }

    public void addCollisionListener(CollisionListener listener) {
        if (this.listeners == null) {
            this.listeners = new ArrayList<CollisionListener>();
        }
        this.listeners.add(listener);
    }

    public void removeCollisionListener(CollisionListener listener) {
        if (this.listeners != null) {
            this.listeners.remove(listener);
        }
    }

    private void notifyListeners(Collision collision) {
        if (this.listeners != null && this.listeners.size() > 0) {
            for (CollisionListener listener : listeners) {
                listener.onCollided(collision);
            }
        }
    }

    public boolean shouldIgnore(GameEntity entity) {
        HashSet<Class<? extends GameEntity>> entitiesToIgnore = getEntitiesToIgnore();
        return entitiesToIgnore != null && entitiesToIgnore.contains(entity.getClass());
    }

    public void addEntityToIgnore(Class<? extends GameEntity>... entitiesToIgnore) {
        if (this.entitiesToIgnore == null) {
            this.entitiesToIgnore = new HashSet<Class<? extends GameEntity>>();
        }
        for(Class<? extends GameEntity> entityType : entitiesToIgnore) {
            this.entitiesToIgnore.add(entityType);
        }
    }

    public void removeEntityFromIgnoredList(Class<? extends GameEntity> entity) {
        if (this.entitiesToIgnore != null && this.entitiesToIgnore.size() > 0) {
            this.entitiesToIgnore.remove(entity);
        }
    }

    public HashSet<Class<? extends GameEntity>> getEntitiesToIgnore() {
        return this.entitiesToIgnore;
    }

    public boolean isTrigger() {
        return isTrigger;
    }

    public void setTrigger(boolean trigger) {
        isTrigger = trigger;
    }

    public boolean isCollidable() {
        return isCollidable;
    }

    public void setCollidable(boolean collidable) {
        isCollidable = collidable;
    }

    public CollisionCheckType getCollisionCheckype() {
        return collisionCheckype;
    }

    public void setCollisionCheckType(CollisionCheckType collisionCheckype) {
        this.collisionCheckype = collisionCheckype;
    }

    @Override
    public void update(GameEntity entity, float delta) {
        if (this.collisionCheckype == CollisionCheckType.PASSIVE) {
            CollisionResults results = CollisionDetector.checkCollision(entity);
            this.resolveCollisions(results);
        }
    }
}
