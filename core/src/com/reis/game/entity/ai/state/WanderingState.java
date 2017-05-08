package com.reis.game.entity.ai.state;

import com.badlogic.gdx.math.Vector2;
import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.StateMachineAI;
import com.reis.game.entity.ai.action.MovementAction;
import com.reis.game.entity.ai.transition.TransitionCondition;
import com.reis.game.entity.components.MovementComponent;

/**
 * Created by bernardoreis on 11/26/16.
 */

public class WanderingState extends State {

    protected int currentWaypointIndex = 0;

    protected Vector2[] waypoints;

    public WanderingState(StateMachineAI ai, Vector2[] waypoints) {
        super(ai);
        this.waypoints = waypoints;
    }

    private Vector2 getNextWaypoint() {
        if (currentWaypointIndex == waypoints.length)
            currentWaypointIndex = 0;
        return waypoints[currentWaypointIndex++];
    }

    public void onEnterState(StateMachineAI ai) {
        MovementAction action = new MovementAction(getNextWaypoint(), DEFAULT_SPEED);
        ai.setCurrentAction(action);
    }

    public static TransitionCondition shouldMoveCondition(final GameEntity entity) {
        return new TransitionCondition() {
            @Override
            public boolean avaliate() {
                return entity.hasComponent(MovementComponent.class);
            }
        };
    }
}
