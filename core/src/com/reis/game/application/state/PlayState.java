package com.reis.game.application.state;

import com.reis.game.application.GameManager;
import com.reis.game.entity.player.Player;
import com.reis.game.mechanics.collision.CollisionManager;
import com.reis.game.scene.GameScene;
import com.reis.game.scene.SceneManager;
import com.reis.game.scene.dialog.DialogManager;
import com.reis.game.scene.loader.SceneLoader;
import com.reis.game.state.GameState;
import com.reis.game.state.quest.QuestManager;

/**
 * Created by bernardoreis on 11/16/16.
 */

public class PlayState extends ApplicationState {

    public PlayState(GameState state) {
        loadState(state);
    }

    private void loadState(GameState state) {
        QuestManager.loadQuests(state);
        DialogManager.loadDialogs(state);
        Player.loadPlayer(state);
        GameScene scene = SceneLoader.loadFromState(state);
        SceneManager.setCurrentScene(scene);
        this.scene = scene;
    }

    @Override
    public void update() {
        GameManager.updateWorld();
        CollisionManager.update();
        scene.update();
    }

    @Override
    public void draw() {
        scene.draw();
    }

    @Override
    public void dispose() {
        scene.dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
