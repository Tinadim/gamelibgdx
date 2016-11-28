package com.reis.game.entity.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.reis.game.application.GameManager;
import com.reis.game.entity.GameEntity;
import com.reis.game.mechanics.CollisionDetector;
import com.reis.game.mechanics.CollisionResults;

/**
 * Created by bernardoreis on 11/25/16.
 */

public class CollisionComponent extends EntityComponent {

    public boolean isTrigger = false;

    public Body body;

    public CollisionComponent(GameEntity entity) {
        super(entity);
        //createBodyForEntity(entity);
    }

    private void createBodyForEntity(GameEntity entity) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;
        bodyDef.position.set(entity.getX(), entity.getY());
        bodyDef.bullet = true;

        body = GameManager.getWorld().createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(entity.getWidth() * .5f, entity.getHeight() * .5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public CollisionResults checkCollision(GameEntity entity, Vector2 movement) {
        CollisionResults results = CollisionDetector.checkCollision(entity, movement);
        this.resolveCollisions(entity, results);
        return results;
    }

    private void resolveCollisions(GameEntity entity, CollisionResults results) {

    }

    public void update(GameEntity entity, float delta) {
        //entity.setX(body.getPosition().x);
        //entity.setY(body.getPosition().y);
    }
}
