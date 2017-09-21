package com.reis.game.entity.ai.action;

import com.reis.game.contants.ActionConstants;
import com.reis.game.entity.ai.EntityController;
import com.reis.game.scene.dialog.DialogManager;

/**
 * Created by bernardoreis on 9/4/17.
 */

public class InteractionAction extends AiAction {

    public InteractionAction() {
        super(ActionConstants.INTRACTION_PRIORITY, ActionConstants.INTERACTION_NAME, -1);
    }

    protected void onStart(EntityController entityController) {
        //DialogManager.showDialogForEntity(entityController.getEntity());
    }
}
