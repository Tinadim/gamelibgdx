package com.reis.game.entity.ai;

import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.action.AiAction;
import com.reis.game.entity.ai.state.State;
import com.reis.game.resource.prototype.AI.AIData;

/**
 * Created by bernardoreis on 11/26/16.
 */

public abstract class StateMachineAI extends EntityController {

    public State currentState, previousState;

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
            currentState.leaveState(this);
        currentState = newState;
        currentState.enterState(this);
    }

    @Override
    public boolean forceAction(AiAction action) {
        State state = action.createStateFromAction(this);
        interruptCurrentState(state);
        return true;
    }

    public void interruptCurrentState(State newState) {
        if (currentState != null) {
            currentState.onPauseState(this);
            previousState = currentState;
        }
        currentState = newState;
        currentState.enterState(this);
    }

    public void resumePreviousState() {
        if (currentState != null) {
            currentState.leaveState(this);
        }
        if (previousState != null) {
            currentState = previousState;
            currentState.onReturnToState(this);
        }
    }

    public State getCurrentState() {
        return currentState;
    }
}
