package com.reis.game.entity.player;

import com.badlogic.gdx.math.Vector2;
import com.reis.game.contants.ActionConstants;
import com.reis.game.entity.EntityFactory;
import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.action.IdleAction;
import com.reis.game.entity.ai.action.MovementAction;
import com.reis.game.entity.components.AiComponent;
import com.reis.game.entity.components.MovementComponent;
import com.reis.game.entity.template.PlayerTemplate;
import com.reis.game.state.GameState;

/**
 * Created by bernardoreis on 11/17/16.
 */

public final class Player extends GameEntity {

    private static final int PLAYER_ID = 1;
    private static Player instance = null;

    public Player(int id, int row, int col) {
        super(id, row, col);
    }

    public static Player getInstance() {
        if (instance == null)
            throw new IllegalStateException("Player must be loaded before being used");
        return instance;
    }

    public static Player loadPlayer(GameState state) {
        instance = EntityFactory.createPlayer(new PlayerTemplate(), state.playerData);
        return instance;
    }

    public void idle() {
        AiComponent component = this.getComponent(AiComponent.class);
        component.getAi().setCurrentAction(new IdleAction());
    }

    public void move(Vector2 direction) {
        direction.add(this.getX(), this.getY());
        AiComponent component = this.getComponent(AiComponent.class);
        MovementComponent movementComponent = this.getComponent(MovementComponent.class);
        component.getAi().setCurrentAction(new MovementAction(direction, ActionConstants.DEFAULT_SPEED));

        /*MovementComponent component = this.getComponent(MovementComponent.class);
        if (!direction.equals(Vector2.Zero)) {
            component.move(this, direction);
        } else {
            component.stop(this);
        }*/
    }
}
