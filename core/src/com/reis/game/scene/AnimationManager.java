package com.reis.game.scene;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.reis.game.contants.SceneConstants;
import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.EntityController;
import com.reis.game.entity.ai.action.AiAction;
import com.reis.game.entity.ai.action.IdleAction;
import com.reis.game.entity.components.EntityControllerComponent;
import com.reis.game.resource.prototype.AnimationProto;
import com.reis.game.resource.prototype.AnimationProto.AnimationPrototype;
import com.reis.game.resource.prototype.AnimationProto.AnimationData;

import java.util.HashMap;
import java.util.List;

/**
 * Created by bernardoreis on 9/24/17.
 */

public final class AnimationManager {

    private static final String ACTION_PACKAGE = "com.reis.game.entity.ai.action";

    private static HashMap<AnimationKey, Animation> animationCache = new HashMap<AnimationKey, Animation>();

    private AnimationManager() {}

    public static Animation getAnimationForEntity(GameEntity entity) {
        if (!entity.hasComponent(EntityControllerComponent.class)) {
            return null;
        }

        EntityControllerComponent component = entity.getComponent(EntityControllerComponent.class);
        EntityController controller = component.getEntityController();
        AiAction currentAction = controller.getCurrentAction();
        int orientation = entity.getOrientation();

        Animation animation = null;
        if (currentAction != null) {
            AnimationKey key = new AnimationKey(currentAction.getClass(), entity, orientation);
            animation = animationCache.get(key);
        }

        if (animation == null) {
            AnimationKey key = new AnimationKey(IdleAction.class, entity, orientation);
            return animationCache.get(key);
        }
        return animation;
    }

    public static void loadAnimations(GameEntity entity, AnimationData animationData) {
        List<AnimationPrototype> animationList = animationData.getAnimationPrototypeList();
        for (AnimationPrototype animation : animationList) {
            loadAnimation(entity, animation);
        }
    }

    private static void loadAnimation(GameEntity entity, AnimationPrototype animationData) {
        int orientation = animationData.getEntityOrientation();
        float frameDuration = animationData.getFrameDuration();
        String atlasName = animationData.getAtlasName();
        String animationName = animationData.getAnimationName();
        String className = ACTION_PACKAGE + "." + animationData.getActionClassName();

        TextureAtlas atlas = TextureManager.loadAtlas(atlasName);
        Array<AtlasRegion> frames = atlas.findRegions(animationName);
        if (orientation == SceneConstants.WEST) {
            flipTextures(frames);
        }

        Animation animation = new Animation(frameDuration, frames, Animation.PlayMode.LOOP);
        storeAnimation(animation, entity, className, orientation);
    }

    private static void flipTextures(Array<AtlasRegion> frames) {
        for (AtlasRegion frame : frames) {
            frame.flip(true, false);
        }
    }

    private static void storeAnimation(Animation animation, GameEntity entity, String className, int orientation) {
        try {
            Class<? extends AiAction> actionClass = (Class<? extends AiAction>) Class.forName(className);
            AnimationKey key = new AnimationKey(actionClass, entity, orientation);
            animationCache.put(key, animation);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class AnimationKey {

        private Class<? extends AiAction> actionClass;
        private GameEntity entity;
        private int orientation;

        AnimationKey(Class<? extends AiAction> actionClass, GameEntity entity, int orientation) {
            this.actionClass = actionClass;
            this.orientation = orientation;
            this.entity = entity;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AnimationKey that = (AnimationKey) o;

            if (orientation != that.orientation) return false;
            if (!actionClass.equals(that.actionClass)) return false;
            return entity.equals(that.entity);

        }

        @Override
        public int hashCode() {
            int result = actionClass.hashCode();
            result = 31 * result + entity.hashCode();
            result = 31 * result + orientation;
            return result;
        }
    }
}
