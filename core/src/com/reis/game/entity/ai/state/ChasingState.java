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

    public ChasingState(GameEntity entityToChase) {
        this(entityToChase, DEFAULT_RANGE_OF_SIGHT);
    }

    public ChasingState(GameEntity entityToChase, int rangeOfSight) {
        this.entityToChase = entityToChase;
        this.rangeOfSight = (int) MapUtils.toCoord(rangeOfSight);
    }

    @Override
    public void onEnterState(StateMachineAI ai) {
        this.action = new MovementAction(entityToChase.getPosition(), DEFAULT_SPEED * .5f);
    }

    @Override
    public void onUpdate(StateMachineAI ai) {
        this.action = new MovementAction(entityToChase.getPosition(), DEFAULT_SPEED * .5f);
        this.action.start(ai);
    }

    private boolean entityInSight(GameEntity entity) {
        Vector2 distance = entity.getPosition().sub(entityToChase.getPosition());
        return Math.abs(distance.x) <= rangeOfSight && Math.abs(distance.y) <= rangeOfSight;
    }

    public TransitionCondition entityNear(final GameEntity entity) {
        return new TransitionCondition() {
            @Override
            public boolean avaliate(StateMachineAI ai) {
                return entityInSight(entity);
            }
        };
    }

    public TransitionCondition entityOutOfSight(final GameEntity entity) {
        return new TransitionCondition() {
            @Override
            public boolean avaliate(StateMachineAI ai) {
                return !entityInSight(entity);
            }
        };
    }
}
