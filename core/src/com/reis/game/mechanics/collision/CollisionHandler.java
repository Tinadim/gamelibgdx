package com.reis.game.mechanics.collision;

import com.reis.game.entity.GameEntity;
import com.reis.game.entity.components.CombatComponent;
import com.reis.game.entity.player.Player;
import com.reis.game.mechanics.battle.EnemyContact;

/**
 * Created by bernardoreis on 11/28/16.
 */

public final class CollisionHandler {

    private CollisionHandler() {}

    public static void handleCollision(GameEntity entity, GameEntity collidedWith) {

        //TODO make this more flexible

        //TODO fire triggers maybe?
        if (collidedWith instanceof Player && entity.hasComponent(CombatComponent.class)) {
            CombatComponent component = collidedWith.getComponent(CombatComponent.class);
            component.onHitTaken(new EnemyContact(entity));
        }

    }
}
