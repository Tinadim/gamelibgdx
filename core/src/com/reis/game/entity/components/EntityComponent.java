package com.reis.game.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.reis.game.entity.GameEntity;

/**
 * Created by bernardoreis on 11/24/16.
 */

public abstract class EntityComponent implements Component {

    public EntityComponent(GameEntity entity) {

    }

    public void update(GameEntity entity, float delta) {

    }

    public void draw(GameEntity entity, Batch batch, float parentAlpha) {

    }
}
