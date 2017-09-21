package com.reis.game.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.reis.game.entity.components.MovementComponent;
import com.reis.game.entity.player.Player;

/**
 * Created by bernardoreis on 9/9/17.
 */

public class InputHandler implements InputProcessor {

    @Override
    public boolean keyDown(int keycode) {
        Player player = Player.getInstance();
        if (keycode == Keys.SPACE) {
            player.executeAction();
            return true;
        }
        if (keycode == Keys.RIGHT) {
            return updatePlayerVelocityX(1);
        }
        if (keycode == Keys.LEFT) {
            return updatePlayerVelocityX(-1);
        }
        if (keycode == Keys.UP) {
            return updatePlayerVelocityY(1);
        }
        if (keycode == Keys.DOWN) {
            return updatePlayerVelocityY(-1);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.RIGHT) {
            return updatePlayerVelocityX(0);
        }
        if (keycode == Keys.LEFT) {
            return updatePlayerVelocityX(0);
        }
        if (keycode == Keys.UP) {
            return updatePlayerVelocityY(0);
        }
        if (keycode == Keys.DOWN) {
            return updatePlayerVelocityY(0);
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private boolean updatePlayerVelocityX(float x) {
        Player player = Player.getInstance();
        MovementComponent component = player.getComponent(MovementComponent.class);
        component.setVelocityX(x);
        return true;
    }

    private boolean updatePlayerVelocityY(float y) {
        Player player = Player.getInstance();
        MovementComponent component = player.getComponent(MovementComponent.class);
        component.setVelocityY(y);
        return true;
    }
}
