package com.reis.game.entity.ai.action;

import com.reis.game.contants.ActionConstants;

/**
 * Created by bernardoreis on 11/26/16.
 */

public class IdleAction extends DurationBasedAction {

    public IdleAction() {
        super(ActionConstants.IDLE_PRIORITY, ActionConstants.IDLE_NAME, -1);
    }

    public IdleAction(float duration) {
        super(ActionConstants.IDLE_PRIORITY, ActionConstants.IDLE_NAME, duration);
    }
}
