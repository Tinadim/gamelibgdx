package com.reis.game.entity.components;

import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.EntityController;
import com.reis.game.entity.ai.action.AiAction;
import com.reis.game.entity.ai.action.InteractionAction;
import com.reis.game.scene.dialog.DialogManager;
import com.reis.game.state.event.Event;
import com.reis.game.state.event.EventType;

/**
 * Created by bernardoreis on 9/3/17.
 */

public class InteractionComponent extends EntityComponent {

    public InteractionComponent(GameEntity entity) {
        super(entity);
    }

    public void interact(GameEntity entity) {
        // TODO fire event only when interaction is over
        Event event = new Event(EventType.ENTITY_INTERACTION, entity);
        event.fire();
        startInteraction(entity);
    }

    private void startInteraction(GameEntity entity) {
        EntityControllerComponent controllerComponent = entity.getComponent(EntityControllerComponent.class);
        if (controllerComponent != null) {
            EntityController controller = controllerComponent.getEntityController();
            controller.forceAction(new InteractionAction());
            DialogManager.showDialogForEntity(entity);
        }
    }

    public void endInteraction() {
        EntityControllerComponent component = this.entity.getComponent(EntityControllerComponent.class);
        if (component != null) {
            EntityController controller = component.getEntityController();
            AiAction action = controller.getCurrentAction();
            if (action != null && action instanceof InteractionAction) {
                action.stop(controller);
            }
        }
    }
}
