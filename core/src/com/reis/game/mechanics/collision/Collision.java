package com.reis.game.mechanics.collision;

import com.reis.game.entity.GameEntity;

import java.awt.geom.Rectangle2D;

/**
 * Created by bernardoreis on 2/25/17.
 */

public class Collision {

    public GameEntity entity;
    public GameEntity collidedWith;
    public Rectangle2D intersection;

    public Collision(GameEntity entity, GameEntity collidedWith) {
        this(entity, collidedWith, null);
    }

    public Collision(GameEntity entity, GameEntity collidedWith, Rectangle2D intersection) {
        this.entity = entity;
        this.collidedWith = collidedWith;
        this.intersection = intersection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Collision collision = (Collision) o;

        if (entity != null ? !entity.equals(collision.entity) : collision.entity != null)
            return false;
        return collidedWith != null ? collidedWith.equals(collision.collidedWith) : collision.collidedWith == null;

    }

    public int hashCode() {
        int result = entity != null ? entity.hashCode() : 0;
        result = 31 * result + (collidedWith != null ? collidedWith.hashCode() : 0);
        return result;
    }
}
