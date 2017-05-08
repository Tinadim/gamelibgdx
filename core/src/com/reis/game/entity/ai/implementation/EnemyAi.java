package com.reis.game.entity.ai.implementation;

import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.StateMachineAI;
import com.reis.game.entity.ai.state.ChasingState;
import com.reis.game.entity.ai.state.IdleState;
import com.reis.game.entity.ai.state.State;
import com.reis.game.entity.ai.state.WanderingState;
import com.reis.game.entity.ai.transition.ActionCompleteCondition;
import com.reis.game.entity.ai.transition.StateTransition;
import com.reis.game.entity.player.Player;
import com.reis.game.resource.prototype.AI.AIData;
import com.reis.game.util.ProtoUtils;

/**
 * Created by bernardoreis on 11/27/16.
 */

public class EnemyAi extends StateMachineAI {

    public EnemyAi(GameEntity entity, AIData data) {
        super(entity, data);
    }

    @Override
    protected State createStates(StateMachineAI ai, AIData aiData) {

        IdleState idleState = new IdleState(ai);
        WanderingState wanderingState = new WanderingState(ai, ProtoUtils.extractWayPoints(aiData));
        ChasingState chasingState = new ChasingState(ai, Player.getInstance());

        StateTransition idleToIdle = new StateTransition(idleState, 0);
        StateTransition idleToWandering = new StateTransition(wanderingState, 1);
        StateTransition idleToChase = new StateTransition(chasingState, 2);
        StateTransition wanderingToIdle = new StateTransition(idleState, 0);
        StateTransition wanderingToChase = new StateTransition(chasingState, 1);
        StateTransition chaseToIdle = new StateTransition(idleState);

        idleToIdle.addCondition(new ActionCompleteCondition(idleState));
        idleToWandering.addCondition(new ActionCompleteCondition(idleState));
        idleToWandering.addCondition(IdleState.createShouldIdleCondition());
        idleToWandering.addCondition(WanderingState.shouldMoveCondition(entity));
        idleToChase.addCondition(chasingState.entityNear());
        wanderingToIdle.addCondition(new ActionCompleteCondition(wanderingState));
        wanderingToChase.addCondition(chasingState.entityNear());
        chaseToIdle.addCondition(chasingState.entityOutOfSight());

        idleState.addTransition(idleToIdle);
        idleState.addTransition(idleToWandering);
        idleState.addTransition(idleToChase);
        wanderingState.addTransition(wanderingToIdle);
        wanderingState.addTransition(wanderingToChase);
        chasingState.addTransition(chaseToIdle);

        idleState.sortTransitions();
        wanderingState.sortTransitions();
        return idleState;
    }
}
