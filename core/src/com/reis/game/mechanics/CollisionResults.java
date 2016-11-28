package com.reis.game.mechanics;

import com.reis.game.entity.GameEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bernardoreis on 11/25/16.
 */

public class CollisionResults {
    public List<GameEntity> entitiesCollided;
    public boolean collided = false;
    public int stepsWalkedX = 0;
    public int stepsWalkedY = 0;

    public void addCollision(GameEntity collidedWith) {
        if (entitiesCollided == null)
            entitiesCollided = new ArrayList<GameEntity>();
        entitiesCollided.add(collidedWith);
    }
}
