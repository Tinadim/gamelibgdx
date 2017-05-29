package com.reis.game.entity.ai.state;

import com.reis.game.contants.ActionConstants;
import com.reis.game.entity.ai.StateMachineAI;
import com.reis.game.entity.ai.transition.StateTransition;
import com.reis.game.entity.ai.action.AiAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by bernardoreis on 11/26/16.
 */

public class State implements ActionConstants {

    protected AiAction action;
    protected List<StateTransition> transitions;

    public State() {
        this(null);
    }

    public State(AiAction action) {
        this(action, new ArrayList<StateTransition>());
    }

    public State(AiAction action, List<StateTransition> transitions) {
        this.action = action;
        this.transitions = transitions;
        this.sortTransitions(transitions);
    }

    public void onUpdate(StateMachineAI ai) {}

    public final void enterState(StateMachineAI ai) {
        this.onEnterState(ai);
        this.action.start(ai);
    }

    protected void onEnterState(StateMachineAI ai) {}

    public final void leaveState(StateMachineAI ai) {
        this.onLeaveState(ai);
        if (!this.action.checkFinished(ai)) {
            this.action.stop(ai);
        }
    }

    protected void onLeaveState(StateMachineAI ai) {}

    public void onPauseState(StateMachineAI ai) {}

    public void onReturnToState(StateMachineAI ai) {}

    public void addTransition(StateTransition transition) {
        this.transitions.add(transition);
    }

    public final void update(StateMachineAI ai, float delta) {
        if (this.action == null) {
            throw new IllegalStateException("Action cannot be null. Either provide in state constructor " +
                    "or instantiate it inside onEnterState");
        }
        this.action.update(ai, delta);
        this.onUpdate(ai);
        this.checkTransitions(ai);
    }

    private void checkTransitions(StateMachineAI ai) {
        for (StateTransition transition : transitions) {
            boolean shouldExecuteTransition = transition.shouldExecute(ai);
            if (shouldExecuteTransition) {
                transition.execute(ai);
                return;
            }
        }
    }

    public void sortTransitions() {
        this.sortTransitions(this.transitions);
    }

    public void sortTransitions(List<StateTransition> transitions) {
        Collections.sort(transitions, new Comparator<StateTransition>() {
            @Override
            public int compare(StateTransition stateTransition, StateTransition other) {
                return other.getPriority() - stateTransition.getPriority();
            }
        });
    }

    public AiAction getAction() {
        return action;
    }
}