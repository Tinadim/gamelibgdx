package com.reis.game.entity.template;

import com.badlogic.gdx.graphics.Color;
import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.implementation.EnemyAi;
import com.reis.game.entity.components.EntityControllerComponent;
import com.reis.game.entity.components.BodyComponent;
import com.reis.game.entity.components.CombatComponent;
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

        entity.add(new SpriteComponent(entity, data, Color.RED));
        entity.add(new BodyComponent(entity));
        entity.add(new CombatComponent(entity));
        entity.add(new MovementComponent(entity));
        entity.add(new EntityControllerComponent(entity, new EnemyAi(entity, aiData)));
    }
}
