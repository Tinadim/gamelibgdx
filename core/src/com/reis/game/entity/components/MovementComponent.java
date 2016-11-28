package com.reis.game.entity.components;

import com.reis.game.contants.ActionConstants;
import com.reis.game.entity.GameEntity;

/**
 * Created by bernardoreis on 11/24/16.
 */

public class MovementComponent extends EntityComponent {

    private float speed = ActionConstants.DEFAULT_SPEED;
    private boolean canMove = true;

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
}
