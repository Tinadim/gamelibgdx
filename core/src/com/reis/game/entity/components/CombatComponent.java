package com.reis.game.entity.components;

import com.reis.game.entity.GameEntity;
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
        SpriteComponent sprite = this.entity.getComponent(SpriteComponent.class);
        if (sprite != null) {
            sprite.blink();
        }
    }
}
