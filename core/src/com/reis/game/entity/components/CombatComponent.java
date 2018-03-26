package com.reis.game.entity.components;

import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.action.KnockbackAction;
import com.reis.game.mechanics.battle.Attack;

/**
 * Created by bernardoreis on 12/25/16.
 */

public class CombatComponent extends EntityComponent {

    private int hp = 1;

    public CombatComponent(GameEntity entity) {
        super(entity);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void onHitTaken(Attack attack) {
        blink(this.entity);
        knockBack(this.entity, attack);
    }

    private void blink(GameEntity entity) {
        SpriteComponent sprite = entity.getComponent(SpriteComponent.class);
        if (sprite != null) {
            sprite.blink();
        }
    }

    private void knockBack(GameEntity entity, Attack attack) {
        EntityControllerComponent component = entity.getComponent(EntityControllerComponent.class);
        if (component != null) {
            KnockbackAction action = new KnockbackAction(attack);
            component.getEntityController().forceAction(action);
        }
    }
}
