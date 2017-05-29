package com.reis.game.entity.ai.action;

import com.reis.game.entity.ai.EntityController;

/**
 * Created by bernardoreis on 5/28/17.
 */

public abstract class DurationBasedAction extends AiAction {

    public float duration = -1;
    private float elapsedTime = 0;

    public DurationBasedAction(int priority, String name, float duration)  {
        super(priority, name);
        this.duration = duration;
    }

    protected void onUpdate(EntityController entityController, float delta) {
        this.elapsedTime += delta;
    }

    @Override
    public boolean checkFinished(EntityController entityController) {
        return this.duration > -1 && this.elapsedTime >= this.duration;
    }
}
