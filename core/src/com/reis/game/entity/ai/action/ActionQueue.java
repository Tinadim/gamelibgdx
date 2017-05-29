package com.reis.game.entity.ai.action;

import com.reis.game.entity.ai.EntityController;

import java.util.LinkedList;

/**
 * Created by bernardoreis on 2/19/17.
 */

public class ActionQueue extends AiAction {

    protected LinkedList<AiAction> actions;
    protected AiAction currentAction;

    public ActionQueue (int priority, String name) {
        super (priority, name);
        this.actions = new LinkedList<AiAction>();
    }

    public ActionQueue (int priority, String name, AiAction... actions) {
        this (priority, name);
        this.add(actions);
    }

    public void add(AiAction... actions) {
        for(AiAction action : actions) {
            this.actions.add(action);
        }
    }

    private void nextAction(EntityController entityController) {
        if (!this.actions.isEmpty()) {
            this.currentAction = this.actions.poll();
            this.currentAction.start(entityController);
        }
    }

    @Override
    public boolean checkFinished(EntityController entityController) {
        return this.actions.isEmpty();
    }

    @Override
    public void onStart(EntityController entityController) {
        nextAction(entityController);
    }

    @Override
    public void onUpdate(EntityController entityController, float delta) {
        if(this.currentAction.checkFinished(entityController)) {
            this.currentAction.onComplete(entityController);
            nextAction(entityController);
        } else {
            this.currentAction.update(entityController, delta);
        }
    }

    @Override
    public void onStop(EntityController entityController) {
        if(this.currentAction != null)
            this.currentAction.stop(entityController);
        this.currentAction = null;
    }

}
