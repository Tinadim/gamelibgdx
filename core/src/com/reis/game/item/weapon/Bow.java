package com.reis.game.item.weapon;

import com.reis.game.entity.GameEntity;
import com.reis.game.entity.components.MovementComponent;
import com.reis.game.mechanics.battle.Attack;
import com.reis.game.mechanics.collision.AttackHitbox;

import static com.reis.game.contants.GameConstants.TILE_SIZE;

/**
 * Created by bernardoreis on 5/2/17.
 */

public class Bow extends Weapon {

    @Override
    public Attack createAttack(GameEntity attacker) {
        Attack attack = new Attack();
        createAttackHitbox(attack, attacker);
        return attack;
    }

    private void createAttackHitbox(Attack attack, GameEntity attacker) {
        AttackHitbox hitbox = new AttackHitbox(attacker, attack);
        hitbox.setDuration(this.attackSpeed);
        hitbox.setTileSize(1, 1);
        calcHitboxPosition(attacker, hitbox);

        MovementComponent movement = new MovementComponent(hitbox);
        hitbox.add(movement);
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
