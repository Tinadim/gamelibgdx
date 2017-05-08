package com.reis.game.mechanics.battle;

import com.reis.game.mechanics.collision.AttackHitbox;
import com.reis.game.util.MapUtils;

/**
 * Created by bernardoreis on 12/25/16.
 */

public class Attack {

    public AttackHitbox hitbox;

    public int baseDamage = 1;

    public float duration = -1;

    public void fire() {
        MapUtils.getForegroundLayer().addActor(hitbox);
    }

}
