package com.reis.game.entity.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.reis.game.entity.GameEntity;

/**
 * Created by bernardoreis on 11/24/16.
 */

public class SpriteComponent extends EntityComponent {

    protected ShapeRenderer shapeRenderer;
    protected Color hitboxColor;

    public SpriteComponent(GameEntity entity, Color color) {
        super(entity);
        this.shapeRenderer = new ShapeRenderer();
        this.hitboxColor = color;
    }

    public void draw(GameEntity entity, Batch batch, float parentAlpha) {
        batch.end();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(hitboxColor);
        shapeRenderer.rect(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
        shapeRenderer.end();
        batch.begin();
    }

}
