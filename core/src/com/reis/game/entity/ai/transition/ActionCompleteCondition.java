package com.reis.game.entity.ai.transition;

import com.reis.game.entity.ai.StateMachineAI;
import com.reis.game.entity.ai.action.AiAction;
import com.reis.game.entity.ai.state.State;

/**
 * Created by bernardoreis on 11/26/16.
 */

public class ActionCompleteCondition extends TransitionCondition {

    @Override
    public boolean avaliate(StateMachineAI ai) {
        State state = ai.getCurrentState();
        AiAction action = state.getAction();
        return action != null && action.checkFinished(ai);
    }
}
