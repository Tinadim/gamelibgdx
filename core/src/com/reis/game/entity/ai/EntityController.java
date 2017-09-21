package com.reis.game.entity.ai;

import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.action.AiAction;

/**
 * Created by bernardoreis on 11/26/16.
 */

public abstract class EntityController {

    protected GameEntity entity;

    protected boolean paused;

    public EntityController(GameEntity entity) {
        this.entity = entity;
    }

    public abstract void update (float delta);

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

    public abstract boolean forceAction(AiAction action);

    public abstract AiAction getCurrentAction();
}