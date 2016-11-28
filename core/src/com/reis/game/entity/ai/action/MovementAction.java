package com.reis.game.entity.ai.action;

import com.badlogic.gdx.math.Vector2;
import com.reis.game.contants.ActionConstants;
import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.AI;
import com.reis.game.entity.components.CollisionComponent;
import com.reis.game.mechanics.CollisionResults;

/**
 * Created by bernardoreis on 11/26/16.
 */

public class MovementAction extends AiAction {

    private Vector2 destination;
    private float speed;

    public MovementAction(Vector2 destination, float speed) {
        super(ActionConstants.MOVE_PRIORITY, ActionConstants.MOVE_NAME);
        if (destination == null)
            throw new IllegalArgumentException("Destination must not be null");
        this.destination = destination;
        this.speed = speed;
    }

    /*public boolean hasArrived(GameEntity entity) {
        CollisionComponent component = entity.getComponent(CollisionComponent.class);
        Vector2 currentPosition = component.body.getPosition();
        Vector2 copy = new Vector2(destination);
        return copy.sub(currentPosition).len() < ActionConstants.EPSILON;
    }*/

    @Override
    public void onUpdate(AI ai, float delta) {
        GameEntity entity = ai.getEntity();
        float remainingX = destination.x - entity.getX();
        float remainingY = destination.y - entity.getY();
        if (remainingX == 0 && remainingY == 0) {
            finished = true;
            return;
        }

        entity.unbindTiles();
        float dX = Math.min(Math.abs(remainingX), Math.abs(speed * delta)) * Math.signum(remainingX);
        float dY = Math.min(Math.abs(remainingY), Math.abs(speed * delta)) * Math.signum(remainingY);

        CollisionComponent collisionComponent = entity.getComponent(CollisionComponent.class);
        if (collisionComponent != null) {
            CollisionResults results = collisionComponent.checkCollision(entity, new Vector2(dX, dY));
            dX = results.stepsWalkedX;
            dY = results.stepsWalkedY;
        }
        entity.moveBy(dX, dY);
        entity.bindTiles();

        /*CollisionComponent component = entity.getComponent(CollisionComponent.class);
        if (destination != null && hasArrived(entity)) {
            component.body.setLinearVelocity(Vector2.Zero);
            destination = null;
        } else if(destination != null) {
            component.body.setLinearVelocity(destination.x * speed, destination.y * speed);
        }*/
    }

}
