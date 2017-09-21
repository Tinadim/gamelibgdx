package com.reis.game.entity.player;

import com.reis.game.entity.GameEntity;
import com.reis.game.entity.components.InteractionComponent;
import com.reis.game.entity.npc.NPC;
import com.reis.game.mechanics.collision.Collision;
import com.reis.game.mechanics.collision.CollisionListener;

/**
 * Created by bernardoreis on 9/3/17.
 */

public class PlayerCollisionListener extends CollisionListener {

    public void onCollisionStarted(Collision collision) {
        GameEntity entity = collision.entity;
        if (entity instanceof Player && (collision.collidedWith.hasComponent(InteractionComponent.class))) {
            ((Player) entity).actionHandler = new PlayerInteractionHandler(collision.collidedWith);
        }
    }

    public void onCollisionEnded(Collision collision) {
        GameEntity entity = collision.entity;
        if (entity instanceof Player) {
            ((Player) entity).actionHandler = new PlayerAttackHandler();
        }
    }
}
