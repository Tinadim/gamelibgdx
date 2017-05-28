package com.reis.game.entity.ai.action;

import com.badlogic.gdx.math.Vector2;
import com.reis.game.contants.ActionConstants;
import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.AI;
import com.reis.game.entity.components.MovementComponent;
import com.reis.game.mechanics.collision.AttackHitbox;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

/**
 * Created by bernardoreis on 5/21/17.
 */

public class KnockbackAction extends AiAction {

    private AttackHitbox damageSource;

    private int knockbackDistanceInTiles = 5;

    public KnockbackAction() {
        super(ActionConstants.KNOCKBACK_PRIORITY, ActionConstants.KNOCKBACK_NAME);
    }

    @Override
    public void onStart(AI ai) {
        super.onStart(ai);
        GameEntity entity = ai.getEntity();
        Vector2 destination = calcDestination(entity);
        MovementComponent component = entity.getComponent(MovementComponent.class);
        if (component != null) {
            component.moveTo(destination, ActionConstants.KNOCKBACK_RUN_SPEED);
        }
    }

    private Vector2 calcDestination(GameEntity entity) {
        float x = entity.getX();
        float y = entity.getY();
        float dX = Math.signum(x - damageSource.getX()) * knockbackDistanceInTiles;
        float dY = Math.signum(y - damageSource.getY()) * knockbackDistanceInTiles;
        return new Vector2(x + dX, y + dY);
    }
}
