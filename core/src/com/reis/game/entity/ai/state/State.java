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

    protected List<StateTransition> transitions;
    protected AiAction action;
    protected StateMachineAI ai;

    public State(StateMachineAI ai) {
        this(ai, null, new ArrayList<StateTransition>());
    }

    public State(StateMachineAI ai, AiAction action) {
        this(ai, action, new ArrayList<StateTransition>());
    }

    public State(StateMachineAI ai, AiAction action, List<StateTransition> transitions) {
        this.ai = ai;
        this.action = action;
        this.transitions = transitions;
        this.sortTransitions(transitions);
    }

    public void onUpdate(StateMachineAI ai) {}

    public void onEnterState(StateMachineAI ai) {}

    public void onLeaveState(StateMachineAI ai) {}

    public void onReturnToState(StateMachineAI ai) {}

    public void addTransition(StateTransition transition) {
        this.transitions.add(transition);
    }

    public final void update(StateMachineAI ai, float delta) {
        this.action.update(ai, delta);
        this.onUpdate(ai);
        this.checkTransitions(ai);
    }

    private void checkTransitions(StateMachineAI ai) {
        for (StateTransition transition : transitions) {
            boolean shouldExecuteTransition = transition.shouldExecute();
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

    public void setAction(AiAction action) {
        this.action = action;
    }
}