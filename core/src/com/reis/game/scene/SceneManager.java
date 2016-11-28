package com.reis.game.scene;

import com.reis.game.entity.player.Player;

/**
 * Created by bernardoreis on 11/16/16.
 */

public final class SceneManager {

    private static GameScene currentScene;
    private static GameScene tempScene;

    private SceneManager() {}

    public static Scene createSplashScreen() {
        return new SplashScreen();
    }

    public static GameScene getCurrentScene() {
        return currentScene;
    }

    public static void setCurrentScene(GameScene currentScene) {
        SceneManager.currentScene = currentScene;
        currentScene.addPlayerToScene(Player.getInstance());
        currentScene.onScreenEntered();
    }

    public static GameScene getTempScene() {
        return tempScene;
    }

    public static void setTempScene(GameScene tempScene) {
        SceneManager.tempScene = tempScene;
    }
}
