package com.reis.game.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.reis.game.application.state.ApplicationState;
import com.reis.game.application.state.InitialState;
import com.reis.game.application.state.PlayState;
import com.reis.game.input.InputHandler;
import com.reis.game.mechanics.collision.CollisionListener;
import com.reis.game.state.GameState;
import com.reis.game.state.StateManager;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by bernardoreis on 11/16/16.
 */

public final class GameManager {

    private static World world;

    static {
        initWorld();
    }

    private GameManager() {

    }

    private static LinkedList<ApplicationState> stateList;

    public static void initApplication() {
        stateList = new LinkedList<ApplicationState>();
        setApplicationState(new InitialState());
    }

    public static ApplicationState getApplicationState() {
        return stateList.peek();
    }

    public static void setApplicationState(ApplicationState state) {
        ApplicationState currentState = null;
        try {
            currentState = stateList.pop();
        } catch (NoSuchElementException e) {

        } finally {
            if(currentState != null) {
                currentState.onStateEnded();
                currentState.dispose();
            }
            stateList.push(state);
            currentState = state;
            currentState.onStateStarted();
        }

    }

    public static void createNewGame() {
        GameState gameState = StateManager.createInitialState();
        PlayState playState = new PlayState(gameState);
        setApplicationState(playState);
        Gdx.input.setInputProcessor(new InputHandler());
    }

    public static void loadGame() {

    }

    public static void initWorld() {
        //world = new World(new Vector2(0, 0f), true);
        //world.setContactListener(new CollisionListener());
    }

    public static World getWorld() {
        return world;
    }

    public static void updateWorld() {
        //world.step(1f/60f, 8, 3);
    }

}
