package com.reis.game.entity.ai.transition;

import com.reis.game.entity.ai.action.AiAction;
import com.reis.game.entity.ai.state.State;

/**
 * Created by bernardoreis on 11/26/16.
 */

public class ActionCompleteCondition extends TransitionCondition {

    private State state;

    public ActionCompleteCondition(State state) {
        this.state = state;
    }

    @Override
    public boolean avaliate() {
        AiAction action = state.getAi().getCurrentAction();
        return action != null && action.isFinished();
    }
}
