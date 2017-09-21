package com.reis.game.entity.player;

import com.badlogic.gdx.math.Vector2;
import com.reis.game.contants.ActionConstants;
import com.reis.game.contants.GameConstants;
import com.reis.game.entity.EntityFactory;
import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.PlayerController;
import com.reis.game.entity.ai.action.IdleAction;
import com.reis.game.entity.ai.action.MovementAction;
import com.reis.game.entity.components.EntityControllerComponent;
import com.reis.game.entity.components.MovementComponent;
import com.reis.game.entity.template.PlayerTemplate;
import com.reis.game.item.weapon.Sword;
import com.reis.game.item.weapon.Weapon;
import com.reis.game.mechanics.collision.CollisionManager;
import com.reis.game.state.GameState;

import static sun.audio.AudioPlayer.player;

/**
 * Created by bernardoreis on 11/17/16.
 */

public final class Player extends GameEntity {

    private static final int PLAYER_ID = 1;
    private static Player instance = null;

    PlayerActionHandler actionHandler = new PlayerAttackHandler();
    Weapon currentWeapon = new Sword();

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
        instance.registerListeners();
        return instance;
    }

    public void idle() {
        getAi().setCurrentAction(new IdleAction());
    }

    public void move() {
        MovementComponent component = this.getComponent(MovementComponent.class);
        Vector2 velocity = new Vector2(component.getVelocity());
        if (!velocity.isZero()) {
            velocity.scl(GameConstants.TILE_SIZE).add(this.getX(), this.getY());
            getAi().setCurrentAction(new MovementAction(velocity, ActionConstants.DEFAULT_SPEED));
        }
    }

    public void executeAction() {
        this.actionHandler.handle(this);
    }

    PlayerController getAi() {
        EntityControllerComponent component = this.getComponent(EntityControllerComponent.class);
        return (PlayerController) component.getEntityController();
    }

    private void registerListeners() {
        CollisionManager.addCollisionListener(new PlayerCollisionListener());
    }
}
