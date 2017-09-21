package com.reis.game.entity.ai;

import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.action.AiAction;
import com.reis.game.entity.ai.action.IdleAction;

/**
 * Created by bernardoreis on 11/26/16.
 */

public class PlayerController extends EntityController {

    private AiAction currentAction;

    public PlayerController(GameEntity entity) {
        super(entity);
        this.setCurrentAction(new IdleAction());
    }

    private void startCurrentAction() {
        currentAction.start(this);
    }

    private void stopCurrentAction() {
        if (currentAction != null) {
            currentAction.stop(this);
        }
    }

    @Override
    public AiAction getCurrentAction() {
        return currentAction;
    }

    public boolean setCurrentAction(AiAction action) {
        if (action == null)
            throw new IllegalArgumentException("Action must not be null");
        if (currentAction == null) {
            currentAction = action;
            startCurrentAction();
            return true;
        } else if (action.getPriority() > currentAction.getPriority()) {
            return setAction(action);
        } else if (action.getPriority() == currentAction.getPriority()) {
            if (!action.getClass().equals(currentAction.getClass()) || action.isSelfReplaceable()) {
                return setAction(action);
            }
        }
        return false;
    }

    private boolean setAction(AiAction action) {
        stopCurrentAction();
        currentAction = action;
        startCurrentAction();
        return true;
    }

    public boolean forceAction(AiAction action) {
        return setCurrentAction(action);
    }

    @Override
    public void update(float delta) {
        if (paused) {
            return;
        } if (currentAction == null) {
            setCurrentAction(new IdleAction());
        } else if (currentAction.checkFinished(this)) {
            currentAction.onComplete(this);
            currentAction = null;
            setCurrentAction(new IdleAction());
        } else {
            currentAction.update(this, delta);
        }
    }
}
