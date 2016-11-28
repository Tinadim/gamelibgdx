package com.reis.game.state;

/**
 * Created by bernardoreis on 11/16/16.
 */

public final class StateManager {

    private StateManager() {}

    public static GameState createInitialState() {
        return new InitialState();
    }

    public static void saveState(GameState state) {

    }

    public static GameState recoverGameState() {
        return new GameState();
    }

    public static void loadState(GameState state) {

    }
}
