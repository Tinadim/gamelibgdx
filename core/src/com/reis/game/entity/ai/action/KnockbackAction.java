package com.reis.game.entity.ai.action;

import com.badlogic.gdx.math.Vector2;
import com.reis.game.contants.ActionConstants;
import com.reis.game.contants.GameConstants;
import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.EntityController;
import com.reis.game.entity.components.MovementComponent;
import com.reis.game.mechanics.battle.Attack;
import com.reis.game.mechanics.collision.AttackHitbox;

/**
 * Created by bernardoreis on 5/21/17.
 */

public class KnockbackAction extends AiAction {

    private Attack damageSource;

    private int knockbackDistanceInTiles = 5;

    public KnockbackAction(Attack damageSource) {
        super(ActionConstants.KNOCKBACK_PRIORITY, ActionConstants.KNOCKBACK_NAME);
        this.damageSource = damageSource;
    }

    @Override
    public void onStart(EntityController entityController) {
        super.onStart(entityController);
        GameEntity entity = entityController.getEntity();
        Vector2 destination = calcDestination(entity);
        MovementComponent component = entity.getComponent(MovementComponent.class);
        if (component != null) {
            component.moveTo(destination, ActionConstants.KNOCKBACK_RUN_SPEED);
        }
    }

    @Override
    public boolean checkFinished(EntityController entityController) {
        GameEntity entity = entityController.getEntity();
        MovementComponent component = entity.getComponent(MovementComponent.class);
        return component != null && component.isStopped();
    }

    private Vector2 calcDestination(GameEntity entity) {
        AttackHitbox hitbox = this.damageSource.hitbox;
        float x = entity.getCenterX();
        float y = entity.getCenterY();
        float dX = Math.signum(x - hitbox.getCenterX()) * knockbackDistanceInTiles * GameConstants.TILE_SIZE;
        float dY = Math.signum(y - hitbox.getCenterY()) * knockbackDistanceInTiles * GameConstants.TILE_SIZE;
        return new Vector2(entity.getX() + dX, entity.getY() + dY);
    }
}
