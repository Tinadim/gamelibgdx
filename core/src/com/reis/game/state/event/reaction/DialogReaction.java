package com.reis.game.state.event.reaction;

import com.reis.game.entity.GameEntity;
import com.reis.game.entity.components.InteractionComponent;
import com.reis.game.scene.SceneManager;
import com.reis.game.scene.dialog.DialogManager;

/**
 * Created by bernardoreis on 9/2/17.
 */

public class DialogReaction extends Reaction {

    private int entityId;

    public DialogReaction(int entityId) {
        this.entityId = entityId;
    }

    @Override
    public void fire() {

    }

}