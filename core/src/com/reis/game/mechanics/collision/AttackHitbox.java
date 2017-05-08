package com.reis.game.mechanics.collision;

import com.badlogic.gdx.graphics.Color;
import com.reis.game.entity.GameEntity;
import com.reis.game.entity.components.BodyComponent;
import com.reis.game.entity.components.CombatComponent;
import com.reis.game.entity.components.SpriteComponent;
import com.reis.game.mechanics.battle.Attack;

/**
 * Created by bernardoreis on 2/19/17.
 */

public class AttackHitbox extends GameEntity implements CollisionListener {

    private float duration = 1;
    private float elapsedTime = 0;

    private final Attack attack;

    public AttackHitbox(GameEntity source, Attack attack) {
        super(-1);
        this.attack = attack;
        this.createBody(source);
    }

    private void createBody(GameEntity source) {
        BodyComponent bodyComponent = new BodyComponent(this);
        bodyComponent.setIgnoreSameType(true);
        bodyComponent.addCollisionListener(this);
        bodyComponent.setCollisionCheckType(BodyComponent.CollisionCheckType.PASSIVE);
        this.add(bodyComponent);

        //TODO remove this
        SpriteComponent sprite = new SpriteComponent(this, Color.YELLOW);
        this.add(sprite);
    }

    @Override
    public void act(float delta) {
        if (this.duration > -1) {
            this.elapsedTime += delta;
            if (this.elapsedTime >= this.duration) {
                this.removeSelf();
                return;
            }
        }
        super.act(delta);
    }

    public void removeSelf() {
        BodyComponent body = this.getComponent(BodyComponent.class);
        body.unbindTiles();
        this.remove();
    }

    @Override
    public void onCollided(Collision collision) {
        GameEntity entity = collision.collidedWith;
        CombatComponent component = entity.getComponent(CombatComponent.class);
        if (component != null) {
            component.onHitTaken(this.attack);
        }
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }
}
