package com.reis.game.entity.components;

import com.badlogic.gdx.math.Vector2;
import com.reis.game.contants.ActionConstants;
import com.reis.game.entity.GameEntity;
import com.reis.game.mechanics.collision.CollisionResults;

/**
 * Created by bernardoreis on 11/24/16.
 */

public class MovementComponent extends EntityComponent {

    private Vector2 destination;

    private float speed = ActionConstants.DEFAULT_SPEED;
    private boolean canMove = true;
    private boolean stopped = true;

    public MovementComponent(GameEntity entity) {
        super(entity);
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public boolean canMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public Vector2 getDestination() {
        return destination;
    }

    public void moveTo(Vector2 destination) {
        this.moveTo(destination, this.speed);
    }

    public void moveTo(Vector2 destination, float speed) {
        this.destination = destination;
        this.speed = speed;
        this.stopped = false;
    }

    private void move(float deltaTime) {
        float remainingX = destination.x - entity.getX();
        float remainingY = destination.y - entity.getY();
        if (remainingX == 0 && remainingY == 0) {
            stop();
        } else {
            float dX = Math.min(Math.abs(remainingX), Math.abs(speed * deltaTime)) * Math.signum(remainingX);
            float dY = Math.min(Math.abs(remainingY), Math.abs(speed * deltaTime)) * Math.signum(remainingY);
            Vector2 distanceToWalk = new Vector2(dX, dY);
            BodyComponent bodyComponent = entity.getComponent(BodyComponent.class);
            if (bodyComponent != null && bodyComponent.isCollidable) {
                CollisionResults results = bodyComponent.checkCollisionForMovement(new Vector2(dX, dY));
                distanceToWalk = results.distanceWalked;
            }

            if (distanceToWalk.isZero()) {
                stop();
            } else {
                entity.moveBy(distanceToWalk);
            }
        }
    }

    private void stop() {
        this.destination = null;
        this.stopped = true;
    }

    @Override
    public void update(GameEntity entity, float deltaTime) {
        if (this.canMove && !this.stopped) {
            move(deltaTime);
        }
    }
}
