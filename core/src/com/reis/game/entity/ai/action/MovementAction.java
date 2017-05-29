package com.reis.game.entity.ai.action;

import com.badlogic.gdx.math.Vector2;
import com.reis.game.contants.ActionConstants;
import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.EntityController;
import com.reis.game.entity.components.MovementComponent;

import static com.reis.game.contants.SceneConstants.EAST;
import static com.reis.game.contants.SceneConstants.NORTH;
import static com.reis.game.contants.SceneConstants.SOUTH;
import static com.reis.game.contants.SceneConstants.WEST;

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

    @Override
    public void onStart(EntityController entityController) {
        super.onStart(entityController);
        GameEntity entity = entityController.getEntity();
        calculateEntityOrientation(entity);

        MovementComponent component = entity.getComponent(MovementComponent.class);
        component.moveTo(destination, speed);
    }

    @Override
    public void onStop(EntityController entityController) {
        GameEntity entity = entityController.getEntity();
        MovementComponent component = entity.getComponent(MovementComponent.class);
        component.setStopped(true);
    }

    @Override
    public boolean checkFinished(EntityController entityController) {
        GameEntity entity = entityController.getEntity();
        MovementComponent component = entity.getComponent(MovementComponent.class);
        return component.isStopped();
    }

    private void calculateEntityOrientation(GameEntity entity) {
        float distanceX = destination.x - entity.getX();
        float distanceY = destination.y - entity.getY();
        int orientation;
        if (Math.abs(distanceX) >= Math.abs(distanceY)) {
            orientation = distanceX >= 0 ? EAST : WEST;
        } else {
            orientation = distanceY >= 0 ? NORTH : SOUTH;
        }
        entity.setOrientation(orientation);
    }
}
