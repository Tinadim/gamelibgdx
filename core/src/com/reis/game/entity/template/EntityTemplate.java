package com.reis.game.entity.template;

import com.reis.game.entity.GameEntity;
import com.reis.game.resource.prototype.EntityTypeProto.EntityData;

/**
 * Created by bernardoreis on 11/20/16.
 */

public class EntityTemplate {

    public String className;
    public int width = 1;
    public int height = 1;

    //TODO create data wrappers for other components just like AiData

    public void applyToEntity(GameEntity entity, EntityData data) {
        entity.setTileSize(width, height);
        initComponents(entity, data);
    }

    public void initComponents(GameEntity entity, EntityData data) {

    }
}
