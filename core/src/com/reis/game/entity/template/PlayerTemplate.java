package com.reis.game.entity.template;

import com.badlogic.gdx.graphics.Color;
import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.PlayerController;
import com.reis.game.entity.components.AiComponent;
import com.reis.game.entity.components.BodyComponent;
import com.reis.game.entity.components.MovementComponent;
import com.reis.game.entity.components.SpriteComponent;
import com.reis.game.resource.prototype.EntityTypeProto.EntityData;

/**
 * Created by bernardoreis on 11/24/16.
 */

public class PlayerTemplate extends EntityTemplate {

    @Override
    public void initComponents(GameEntity entity, EntityData data) {
        entity.add(new SpriteComponent(entity, Color.BLACK));
        entity.add(new AiComponent(entity, new PlayerController(entity)));
        entity.add(new MovementComponent(entity));
        entity.add(new BodyComponent(entity));
    }

}
