package com.reis.game.entity.components;

import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.EntityController;

/**
 * Created by bernardoreis on 11/24/16.
 */

public class EntityControllerComponent extends EntityComponent {

    private EntityController entityController;

    public EntityControllerComponent(GameEntity entity, EntityController entityController) {
        super(entity);
        this.entityController = entityController;
    }

    public EntityController getEntityController() {
        return entityController;
    }

    @Override
    public void update(GameEntity entity, float delta) {
        this.entityController.update(delta);
    }
}
