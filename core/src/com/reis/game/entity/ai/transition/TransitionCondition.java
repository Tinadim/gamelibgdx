package com.reis.game.entity.ai.transition;

import com.reis.game.entity.ai.StateMachineAI;

/**
 * Created by bernardoreis on 11/26/16.
 */

public abstract class TransitionCondition {

    public abstract boolean avaliate(StateMachineAI ai);

}
