package com.reis.game.entity.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.reis.game.entity.GameEntity;
import com.reis.game.resource.prototype.EntityTypeProto.EntityData;
import com.reis.game.scene.AnimationManager;

/**
 * Created by bernardoreis on 11/24/16.
 */

public class SpriteComponent extends EntityComponent {

    private TextureRegion texture;
    private Animation animation;
    private ShapeRenderer shapeRenderer;
    private Color hitboxColor;

    private float stateTime = 0;

    public SpriteComponent(GameEntity entity, Color color) {
        super(entity);
        this.shapeRenderer = new ShapeRenderer();
        this.hitboxColor = color;
    }

    public SpriteComponent(GameEntity entity, EntityData entityData, Color color) {
        this(entity, color);
        if (entityData.hasAnimationData()) {
            AnimationManager.loadAnimations(entity, entityData.getAnimationData());
        }
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        if (animation != null && this.animation != animation) {
            this.animation = animation;
            this.stateTime = 0;
        }
    }

    public void blink() {
        Action blink = Actions.repeat(10, Actions.sequence(Actions.fadeOut(0.15f), Actions.fadeIn(0.15f)));
        this.entity.addAction(blink);
    }

    public void draw(GameEntity entity, Batch batch, float parentAlpha) {
        checkAnimationChanged(entity);
        drawShape(batch, parentAlpha);

        if (this.animation != null) {
            updateAnimation(batch);
        } else if (this.texture != null) {
            drawTexture();
        } else {
            //drawShape(batch, parentAlpha);
        }
    }

    private void checkAnimationChanged(GameEntity entity) {
        Animation animation = AnimationManager.getAnimationForEntity(entity);
        this.setAnimation(animation);
    }

    private void updateAnimation(Batch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = this.animation.getKeyFrame(stateTime, true);
        float x = entity.getX() - (currentFrame.getRegionWidth() - entity.getWidth()) * 0.5f;
        batch.draw(currentFrame, x, entity.getY());
    }

    private void drawTexture() {

    }

    private void drawShape(Batch batch, float parentAlpha) {
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
