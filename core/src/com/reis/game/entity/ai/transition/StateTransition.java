package com.reis.game.entity.ai.transition;

import com.reis.game.entity.ai.StateMachineAI;
import com.reis.game.entity.ai.state.State;
import com.reis.game.util.ConditionEvaluator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bernardoreis on 11/26/16.
 */

public class StateTransition {

    private List<TransitionCondition> conditions;
    private State nextState;
    private int priority = 0;

    public StateTransition(State nextState) {
        this(nextState, 0);
    }

    public StateTransition(State nextState, int priority) {
        this(nextState, priority, null);
    }

    public StateTransition(State nextState, int priority, List<TransitionCondition> conditions) {
        this.conditions = conditions;
        this.nextState = nextState;
        this.priority = priority;
    }

    public boolean shouldExecute() {
        if (conditions == null || conditions.isEmpty())
            return true;
        return ConditionEvaluator.areConditionsFulfilled(conditions);
    }

    public void execute(StateMachineAI ai) {
        ai.setState(nextState);
    }

    public void addCondition(TransitionCondition condition) {
        if (this.conditions == null)
            this.conditions = new ArrayList<TransitionCondition>();
        this.conditions.add(condition);
    }

    public List<TransitionCondition> getConditions() {
        return conditions;
    }

    public void setConditions(List<TransitionCondition> conditions) {
        this.conditions = conditions;
    }

    public State getNextState() {
        return nextState;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
