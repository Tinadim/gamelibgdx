package com.reis.game.entity.ai;

import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.action.AiAction;

/**
 * Created by bernardoreis on 11/26/16.
 */

public abstract class AI {

    protected GameEntity entity;
    protected AiAction currentAction ;
    protected boolean paused;

    public AI (GameEntity entity) {
        this.entity = entity;
    }

    public abstract void update (float delta);

    public AiAction getCurrentAction () {
        return currentAction;
    }

    public boolean setCurrentAction(AiAction action) {
        currentAction = action;
        return true;
    }

    public GameEntity getEntity() {
        return entity;
    }

    public void setEntity(GameEntity entity) {
        this.entity = entity;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}