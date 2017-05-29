package com.reis.game.entity.ai.state;

import com.reis.game.entity.ai.StateMachineAI;
import com.reis.game.entity.ai.action.AiAction;
import com.reis.game.entity.ai.action.IdleAction;
import com.reis.game.entity.ai.transition.TransitionCondition;
import com.reis.game.resource.ResourceManager;
import com.reis.game.util.Utils;

/**
 * Created by bernardoreis on 11/26/16.
 */

public class IdleState extends State {

    @Override
    public void onEnterState(StateMachineAI ai) {
        this.action = createRandomIdleAction();
    }

    private AiAction createRandomIdleAction() {
        int idleTurns = Utils.randomInt(DEFAULT_MIN_IDLE_TURNS, DEFAULT_MAX_IDLE_TURNS);
        float duration = idleTurns * BASE_TURN_DURATION;
        return new IdleAction(duration);
    }

    public static TransitionCondition createShouldIdleCondition() {
        return new TransitionCondition() {
            @Override
            public boolean avaliate(StateMachineAI ai) {
                return ResourceManager.random.nextBoolean();
            }
        };
    }

}
