package com.reis.game.entity.ai;

import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.action.AiAction;
import com.reis.game.entity.ai.state.State;
import com.reis.game.resource.prototype.AI.AIData;

/**
 * Created by bernardoreis on 11/26/16.
 */

public abstract class StateMachineAI extends AI {

    public State currentState;

    public StateMachineAI (GameEntity entity, AIData aiData) {
        super(entity);
        State initialState = createStates(this, aiData);
        this.setState(initialState);
    }

    protected abstract State createStates(StateMachineAI ai, AIData aiData);

    @Override
    public void update(float delta) {
        if (paused)
            return;
        currentState.update(this, delta);
    }

    public void setState(State newState) {
        if (currentState != null)
            currentState.onLeaveState(this);
        currentState = newState;
        currentState.onEnterState(this);
    }

    @Override
    public boolean setCurrentAction(AiAction action) {
        super.setCurrentAction(action);
        currentAction.start(this);
        return true;
    }
}
