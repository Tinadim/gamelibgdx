package com.reis.game.mechanics.collision;

import com.badlogic.gdx.graphics.Color;
import com.reis.game.entity.GameEntity;
import com.reis.game.entity.components.BodyComponent;
import com.reis.game.entity.components.CombatComponent;
import com.reis.game.entity.components.SpriteComponent;
import com.reis.game.mechanics.battle.Attack;
import com.reis.game.util.Filter;

/**
 * Created by bernardoreis on 2/19/17.
 */

public class AttackHitbox extends GameEntity {

    private float duration = 0;
    private float elapsedTime = 0;

    private final Attack attack;
    private AttackHitboxCollisionListener collisionListener;

    public AttackHitbox(GameEntity source, Attack attack) {
        super(-1);
        this.attack = attack;
        this.duration = attack.duration;
        this.createBody(source);
    }

    private void createBody(GameEntity source) {
        BodyComponent bodyComponent = new BodyComponent(this);
        bodyComponent.setCollidable(false);
        bodyComponent.setIgnoreSameType(true);
        bodyComponent.setCollisionCheckType(BodyComponent.CollisionCheckType.PASSIVE);
        this.add(bodyComponent);

        //TODO remove this
        SpriteComponent sprite = new SpriteComponent(this, Color.YELLOW);
        this.add(sprite);
        this.collisionListener = new AttackHitboxCollisionListener();
        CollisionManager.addCollisionListener(collisionListener);
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
        CollisionManager.removeCollisionListener(this.collisionListener);
        BodyComponent body = this.getComponent(BodyComponent.class);
        body.unbindTiles();
        this.remove();

    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public Attack getAttack() {
        return attack;
    }

    private class AttackHitboxCollisionListener extends CollisionListener {

        AttackHitboxCollisionListener() {
            this.filter = new Filter<Collision>() {
                @Override
                public boolean test(Collision e) {
                    return e.entity instanceof AttackHitbox;
                }
            };
        }

        @Override
        public void onCollisionStarted(Collision collision) {
            GameEntity entity = collision.collidedWith;
            CombatComponent component = entity.getComponent(CombatComponent.class);
            if (component != null) {
                component.onHitTaken(AttackHitbox.this.attack);
            }
        }

        @Override
        public void onCollisionEnded(Collision collision) {

        }
    }
}
