package com.reis.game.entity.player;

import com.reis.game.entity.ai.action.AttackAction;
import com.reis.game.mechanics.battle.Attack;

/**
 * Created by bernardoreis on 9/4/17.
 */

public class PlayerAttackHandler implements PlayerActionHandler {

    @Override
    public void handle(Player player) {
        if (player.getAi().getCurrentAction() instanceof AttackAction) {
            return;
        }
        Attack attack = player.currentWeapon.createAttack(player);
        AttackAction action = new AttackAction(attack);
        player.getAi().setCurrentAction(action);
    }
}
