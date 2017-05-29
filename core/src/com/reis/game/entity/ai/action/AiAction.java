package com.reis.game.entity.ai.action;

import com.reis.game.entity.ai.EntityController;
import com.reis.game.entity.ai.StateMachineAI;
import com.reis.game.entity.ai.state.State;
import com.reis.game.entity.ai.transition.ActionCompleteCondition;
import com.reis.game.entity.ai.transition.StateTransition;

/**
 * Created by bernardoreis on 11/26/16.
 */

public class AiAction {

    public String name;
    public int priority;
    public boolean finished;
    public boolean paused;

    protected boolean selfReplaceable = true;

    public AiAction() {}

    public AiAction (int priority, String name) {
        this (priority, name, -1);
    }

    public AiAction(int priority, String name, float duration)  {
        this.name = name;
        this.priority = priority;
        this.finished = false;
        this.paused = false;
    }

    public int getPriority() {
        return priority;
    }

    public final void start(EntityController entityController) {
        this.onStart(entityController);
    }

    public final void stop(EntityController entityController) {
        this.finished = true;
        this.onStop(entityController);
    }

    public final void update(EntityController entityController, float delta) {
        if (paused)
            return;
        boolean finished = checkFinished(entityController);
        if (!finished)
            onUpdate(entityController, delta);
    }

    public boolean checkFinished(EntityController entityController) {
        return false;
    }

    public boolean isSelfReplaceable() {
        return selfReplaceable;
    }

    public void setSelfReplaceable(boolean selfReplaceable) {
        this.selfReplaceable = selfReplaceable;
    }

    protected void onStart(EntityController entityController) {}

    protected void onStop(EntityController entityController) {}

    protected void onUpdate(EntityController entityController, float delta) {}

    public void onComplete(EntityController entityController) {}

    public State createStateFromAction(StateMachineAI ai) {
        State state = new State(this);
        StateTransition transition = new StateTransition(ai.getCurrentState());
        transition.addCondition(new ActionCompleteCondition());
        state.addTransition(transition);
        return state;
    }
}
