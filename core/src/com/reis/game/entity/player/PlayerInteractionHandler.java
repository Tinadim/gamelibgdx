package com.reis.game.entity.player;

import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.PlayerController;
import com.reis.game.entity.ai.action.AiAction;
import com.reis.game.entity.ai.action.InteractionAction;
import com.reis.game.entity.components.InteractionComponent;
import com.reis.game.scene.dialog.DialogManager;

/**
 * Created by bernardoreis on 9/4/17.
 */

public class PlayerInteractionHandler implements PlayerActionHandler {

    private GameEntity entity;

    public PlayerInteractionHandler(GameEntity entity) {
        this.entity = entity;
    }

    @Override
    public void handle(Player player) {
        // TODO maybe abstract this logic to other entities?
        PlayerController controller = player.getAi();
        AiAction action = controller.getCurrentAction();
        if (action instanceof InteractionAction) {
            updateCurrentInteraction(player);
        } else {
            interact(player, controller);
        }
    }

    private void updateCurrentInteraction(Player player) {
        boolean ended = DialogManager.updateCurrentDialog();
        if (ended) {
            endInteraction(player);
        }
    }

    private void endInteraction(Player player) {
        InteractionComponent interactionComponent = player.getComponent(InteractionComponent.class);
        interactionComponent.endInteraction();
        interactionComponent = this.entity.getComponent(InteractionComponent.class);
        interactionComponent.endInteraction();
    }

    private void interact(Player player, PlayerController controller) {
        boolean actionSet = controller.forceAction(new InteractionAction());
        if (actionSet) {
            adjustEntityOrientation(player);
            InteractionComponent component = player.getComponent(InteractionComponent.class);
            component.interact(this.entity);
        }
    }

    private void adjustEntityOrientation(Player player) {
        int playerOrientation = player.getOrientation();
        int orientation = (playerOrientation + 2) % 4;
        this.entity.setOrientation(orientation);
    }
}
