package com.reis.game.mechanics.collision;

import com.reis.game.util.Filter;

/**
 * Created by bernardoreis on 11/23/16.
 */

public abstract class CollisionListener {

    public Filter<Collision> filter;

    public CollisionListener() {
        this(null);
    }

    public CollisionListener(Filter<Collision> filter) {
        this.filter = filter;
    }

    public abstract void onCollisionStarted(Collision collision);

    public abstract void onCollisionEnded(Collision collision);
}
