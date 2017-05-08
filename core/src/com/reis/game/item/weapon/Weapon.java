package com.reis.game.item.weapon;

import com.reis.game.entity.GameEntity;
import com.reis.game.item.Item;
import com.reis.game.mechanics.battle.Attack;

/**
 * Created by bernardoreis on 12/26/16.
 */

public class Weapon extends Item {

    protected float attackSpeed = 1;

    public Attack createAttack(GameEntity attacker) {
        return new Attack();
    }

}
