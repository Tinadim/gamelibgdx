package com.reis.game.entity.ai.action;

import com.reis.game.contants.ActionConstants;
import com.reis.game.entity.ai.EntityController;
import com.reis.game.mechanics.battle.Attack;

/**
 * Created by bernardoreis on 12/26/16.
 */

public class AttackAction extends DurationBasedAction {

    private Attack attack;

    public AttackAction(Attack attack) {
        super(ActionConstants.ATTACK_PRIORITY, ActionConstants.ATTACK_NAME,
                attack.duration);
        this.attack = attack;
        this.selfReplaceable = false;
    }

    public void onStart(EntityController entityController) {
        this.attack.fire();
    }
}
