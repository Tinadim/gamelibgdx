package com.reis.game.entity.template;

import com.badlogic.gdx.graphics.Color;
import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.implementation.EnemyAi;
import com.reis.game.entity.components.AiComponent;
import com.reis.game.entity.components.CollisionComponent;
import com.reis.game.entity.components.MovementComponent;
import com.reis.game.entity.components.SpriteComponent;
import com.reis.game.resource.prototype.AI.AIData;
import com.reis.game.resource.prototype.EntityTypeProto.EntityData;

/**
 * Created by bernardoreis on 11/27/16.
 */

public class EnemyTemplate extends EntityTemplate {

    public void initComponents(GameEntity entity, EntityData data) {

        AIData aiData = data.getAiData();

        entity.add(new SpriteComponent(entity, Color.RED));
        entity.add(new CollisionComponent(entity));
        entity.add(new MovementComponent(entity));
        entity.add(new AiComponent(entity, new EnemyAi(entity, aiData)));
    }
}
