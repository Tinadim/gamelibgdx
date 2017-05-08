package com.reis.game.item.weapon;

import com.reis.game.entity.GameEntity;
import com.reis.game.mechanics.battle.Attack;
import com.reis.game.mechanics.collision.AttackHitbox;

import static com.reis.game.contants.GameConstants.TILE_SIZE;

/**
 * Created by bernardoreis on 12/26/16.
 */

public class Sword extends Weapon {

    @Override
    public Attack createAttack(GameEntity attacker) {
        Attack attack = new Attack();
        attack.duration = this.attackSpeed;
        attack.hitbox = createAttackHitbox(attack, attacker);
        return attack;
    }

    private AttackHitbox createAttackHitbox(Attack attack, GameEntity attacker) {
        AttackHitbox hitbox = new AttackHitbox(attacker, attack);
        hitbox.setDuration(attack.duration);
        hitbox.setTileSize(1, 1);
        calcHitboxPosition(attacker, hitbox);
        return hitbox;
    }

    private void calcHitboxPosition(GameEntity source, AttackHitbox hitbox) {
        int orientation = source.getOrientation();
        float baseY = source.getY();
        float baseX = source.getX();
        switch (orientation) {
            case 0:
                hitbox.setPosition(baseX, baseY + TILE_SIZE);
                break;
            case 1:
                hitbox.setPosition(baseX + TILE_SIZE, baseY);
                break;
            case 2:
                hitbox.setPosition(baseX, baseY - TILE_SIZE);
                break;
            case 3:
                hitbox.setPosition(baseX - TILE_SIZE, baseY);
                break;
            default:
                throw new IllegalStateException("Invalid orientation: " + orientation);
        }
    }
}
