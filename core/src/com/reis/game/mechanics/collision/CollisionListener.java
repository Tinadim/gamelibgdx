package com.reis.game.mechanics.collision;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.reis.game.entity.GameEntity;

/**
 * Created by bernardoreis on 11/23/16.
 */

public interface CollisionListener {

    void onCollided(Collision collision);
}
