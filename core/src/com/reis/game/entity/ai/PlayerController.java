package com.reis.game.entity.ai;

import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.action.AiAction;
import com.reis.game.entity.ai.action.IdleAction;

/**
 * Created by bernardoreis on 11/26/16.
 */

public class PlayerController extends AI {

    public PlayerController(GameEntity entity) {
        super(entity);
        this.setCurrentAction(new IdleAction());
    }

    @Override
    public boolean setCurrentAction(AiAction action) {
        if (action == null)
            throw new IllegalArgumentException("Action must not be null");
        if (currentAction == null) {
            currentAction = action;
            startCurrentAction();
            return true;
        }
        else if (action.getPriority() >= currentAction.getPriority()) {
            stopCurrentAction();
            currentAction = action;
            startCurrentAction();
            return true;
        }
        return false;
    }

    @Override
    public void update(float delta) {
        if (paused) {
            return;
        } if (currentAction == null) {
            setCurrentAction(new IdleAction());
        } else if (currentAction.isFinished()) {
            currentAction.onComplete(this);
            currentAction = null;
            setCurrentAction(new IdleAction());
        } else {
            currentAction.update(this, delta);
        }
    }

    public void startCurrentAction() {
        currentAction.start(this);
    }

    public void stopCurrentAction() {
        currentAction.stop(this);
    }

}
