package com.reis.game.entity.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
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

    public void blink() {
        Action blink = Actions.repeat(10, Actions.sequence(Actions.fadeOut(0.15f), Actions.fadeIn(0.15f)));
        this.entity.addAction(blink);
    }

    public void draw(GameEntity entity, Batch batch, float parentAlpha) {
        batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(hitboxColor.r, hitboxColor.g, hitboxColor.b, parentAlpha);
        shapeRenderer.rect(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        batch.begin();
    }

}
