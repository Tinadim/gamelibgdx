package com.reis.game.mechanics.collision;

import com.badlogic.gdx.math.Vector2;
import com.reis.game.entity.GameEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by bernardoreis on 11/25/16.
 */

public class CollisionResults {
    public HashSet<Collision> collisions;
    public Vector2 distanceWalked = new Vector2(0, 0);
    public boolean collided = false;

    public void addCollision(Collision collision) {
        if (collisions == null)
            collisions = new HashSet<Collision>();
        collisions.add(collision);
    }
}
