package com.reis.game.entity.ai.action;

import com.reis.game.contants.ActionConstants;
import com.reis.game.entity.ai.AI;
import com.reis.game.mechanics.battle.Attack;

/**
 * Created by bernardoreis on 12/26/16.
 */

public class AttackAction extends AiAction {

    private Attack attack;

    public AttackAction(Attack attack) {
        super(ActionConstants.ATTACK_PRIORITY, ActionConstants.ATTACK_NAME,
                attack.duration);
        this.attack = attack;
        this.selfReplaceable = false;
    }

    public void onStart(AI ai) {
        this.attack.fire();
    }
}
