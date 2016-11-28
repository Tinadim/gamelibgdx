package com.reis.game.mechanics;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Created by bernardoreis on 11/23/16.
 */

public class CollisionListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        contact.getFixtureA().getBody().setLinearVelocity(0, 0);
        contact.getFixtureB().getBody().setLinearVelocity(0, 0);
        /*Object entityA = contact.getFixtureA().getBody().getUserData();
        Object entityB = contact.getFixtureB().getBody().getUserData();
        if (entityA instanceof PhysicsEntity)
            ((PhysicsEntity) entityA).onCollide(entityB);
        if (entityB instanceof PhysicsEntity)
            ((PhysicsEntity) entityB).onCollide(entityA);
            */
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
