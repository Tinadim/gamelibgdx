package com.reis.game.entity.player;

import com.badlogic.gdx.math.Vector2;
import com.reis.game.contants.ActionConstants;
import com.reis.game.entity.EntityFactory;
import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.PlayerController;
import com.reis.game.entity.ai.action.AttackAction;
import com.reis.game.entity.ai.action.IdleAction;
import com.reis.game.entity.ai.action.MovementAction;
import com.reis.game.entity.components.AiComponent;
import com.reis.game.entity.template.PlayerTemplate;
import com.reis.game.item.weapon.Sword;
import com.reis.game.item.weapon.Weapon;
import com.reis.game.mechanics.battle.Attack;
import com.reis.game.state.GameState;

/**
 * Created by bernardoreis on 11/17/16.
 */

public final class Player extends GameEntity {

    private static final int PLAYER_ID = 1;
    private static Player instance = null;

    private Weapon currentWeapon = new Sword();

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
        getAi().setCurrentAction(new MovementAction(direction, ActionConstants.DEFAULT_SPEED));
        /*MovementComponent component = this.getComponent(MovementComponent.class);
        if (!direction.equals(Vector2.Zero)) {
            component.move(this, direction);
        } else {
            component.stop(this);
        }*/
    }

    public void attack() {
        Attack attack = this.currentWeapon.createAttack(this);
        AttackAction action = new AttackAction(attack);
        getAi().setCurrentAction(action);
    }

    private PlayerController getAi() {
        AiComponent component = this.getComponent(AiComponent.class);
        return (PlayerController) component.getAi();
    }
}
