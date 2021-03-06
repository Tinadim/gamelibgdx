package com.reis.game.entity.ai.implementation;

import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.StateMachineAI;
import com.reis.game.entity.ai.state.IdleState;
import com.reis.game.entity.ai.state.InteractingState;
import com.reis.game.entity.ai.state.State;
import com.reis.game.entity.ai.state.WanderingState;
import com.reis.game.entity.ai.transition.ActionCompleteCondition;
import com.reis.game.entity.ai.transition.StateTransition;
import com.reis.game.resource.prototype.AI.AIData;
import com.reis.game.util.ProtoUtils;

/**
 * Created by bernardoreis on 11/26/16.
 */

public class WanderingAi extends StateMachineAI {

    public WanderingAi(GameEntity entity, AIData data) {
        super(entity, data);
    }

    @Override
    protected State createStates(StateMachineAI ai, AIData aiData) {

        State idleState = new IdleState();
        State wanderingState = new WanderingState(ProtoUtils.extractWayPoints(aiData));
        State interactingState = new InteractingState();

        StateTransition idleToIdle = new StateTransition(idleState, 0);
        StateTransition idleToWandering = new StateTransition(wanderingState, 1);
        StateTransition wanderingToIdle = new StateTransition(idleState);
        StateTransition interactingToIdle = new StateTransition(idleState);

        idleToIdle.addCondition(new ActionCompleteCondition());
        idleToWandering.addCondition(new ActionCompleteCondition());
        idleToWandering.addCondition(IdleState.createShouldIdleCondition());
        idleToWandering.addCondition(WanderingState.shouldMoveCondition(entity));
        wanderingToIdle.addCondition(new ActionCompleteCondition());
        interactingToIdle.addCondition(new ActionCompleteCondition());

        idleState.addTransition(idleToIdle);
        idleState.addTransition(idleToWandering);
        wanderingState.addTransition(wanderingToIdle);
        interactingState.addTransition(interactingToIdle);

        idleState.sortTransitions();

        return idleState;
    }

}
