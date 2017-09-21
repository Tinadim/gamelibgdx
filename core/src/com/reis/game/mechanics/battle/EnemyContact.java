package com.reis.game.mechanics.battle;

import com.reis.game.entity.GameEntity;
import com.reis.game.mechanics.collision.AttackHitbox;

/**
 * Created by bernardoreis on 5/29/17.
 */

public class EnemyContact extends Attack {

    // TODO maybe this should be extended to other sources of damage

    public EnemyContact(GameEntity enemy) {
        this.duration = 0;
        this.hitbox = createHitboxFromEnemy(enemy);
    }

    private AttackHitbox createHitboxFromEnemy(GameEntity entity) {
        AttackHitbox hitbox = new AttackHitbox(entity, this);
        hitbox.setTileSize(entity.getTileWidth(), entity.getTileHeight());
        hitbox.setPosition(entity.getX(), entity.getY());
        return hitbox;
    }
}
