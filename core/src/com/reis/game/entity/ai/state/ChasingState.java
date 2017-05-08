package com.reis.game.entity.ai.state;

import com.badlogic.gdx.math.Vector2;
import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.StateMachineAI;
import com.reis.game.entity.ai.action.MovementAction;
import com.reis.game.entity.ai.transition.TransitionCondition;
import com.reis.game.util.MapUtils;

/**
 * Created by bernardoreis on 11/27/16.
 */

public class ChasingState extends State {

    private GameEntity entityToChase;
    private int rangeOfSight;

    public ChasingState(StateMachineAI ai, GameEntity entityToChase) {
        this(ai, entityToChase, DEFAULT_RANGE_OF_SIGHT);
    }

    public ChasingState(StateMachineAI ai, GameEntity entityToChase, int rangeOfSight) {
        super(ai);
        this.entityToChase = entityToChase;
        this.rangeOfSight = (int) MapUtils.toCoord(rangeOfSight);
    }

    @Override
    public void onEnterState(StateMachineAI ai) {
        ai.setCurrentAction(new MovementAction(entityToChase.getPosition(), DEFAULT_SPEED * .5f));
    }

    @Override
    public void onUpdate(StateMachineAI ai) {
        ai.setCurrentAction(new MovementAction(entityToChase.getPosition(), DEFAULT_SPEED * .5f));
    }

    private boolean entityInSight() {
        Vector2 distance = this.ai.getEntity().getPosition().sub(entityToChase.getPosition());
        return Math.abs(distance.x) <= rangeOfSight && Math.abs(distance.y) <= rangeOfSight;
    }

    public TransitionCondition entityNear() {
        return new TransitionCondition() {
            @Override
            public boolean avaliate() {
                return entityInSight();
            }
        };
    }

    public TransitionCondition entityOutOfSight() {
        return new TransitionCondition() {
            @Override
            public boolean avaliate() {
                return !entityInSight();
            }
        };
    }
}
