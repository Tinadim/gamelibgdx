package com.reis.game.entity.ai.action;

import com.reis.game.entity.ai.AI;

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

    private void nextAction(AI ai) {
        if(this.actions.isEmpty())
            this.finished = true;
        else {
            this.currentAction = this.actions.poll();
            this.currentAction.start(ai);
        }
    }

    @Override
    public void start(AI ai) {
        nextAction(ai);
    }

    @Override
    public void update(AI ai, float delta) {
        if(this.currentAction.isFinished()) {
            this.currentAction.onComplete(ai);
            nextAction(ai);
        } else {
            this.currentAction.update(ai, delta);
        }
    }

    @Override
    public void stop(AI ai) {
        if(this.currentAction != null)
            this.currentAction.stop(ai);
        this.currentAction = null;
    }

}
