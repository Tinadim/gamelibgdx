package com.reis.game.scene;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.reis.game.entity.GameEntity;

/**
 * Created by bernardoreis on 11/20/16.
 */

public class CameraHandler extends Actor {

    public static final float DEFAULT_LERP = 10f;

    private GameEntity entityToFollow;
    private boolean boundsEnabled = true;
    private float lerp = DEFAULT_LERP;

    public CameraHandler() {}

    @Override
    public void act(float delta) {
        Camera camera = this.getStage().getCamera();
        Map map = ((GameScene) SceneManager.getCurrentScene()).getMap();
        if (entityToFollow != null)
            updateCameraPosition(camera, delta);
        if (boundsEnabled)
            checkCameraBounds(camera, map);
        camera.update();
        updateMapView(camera, map);
    }

    private void updateCameraPosition(Camera camera, float deltaTime) {
        Vector3 position = camera.position;
        position.x += (entityToFollow.getX() - position.x) * lerp * deltaTime;
        position.y += (entityToFollow.getY() - position.y) * lerp * deltaTime;
    }

    private void checkCameraBounds(Camera camera, Map map) {
        if (map == null)
            return;
        float cameraHalfWidth = camera.viewportWidth * .5f;
        float cameraHalfHeight = camera.viewportHeight * .5f;
        camera.position.x = MathUtils.clamp(camera.position.x, cameraHalfWidth, map.getWidth() - cameraHalfWidth);
        camera.position.y = MathUtils.clamp(camera.position.y, cameraHalfHeight, map.getHeight() - cameraHalfHeight);
    }

    private void updateMapView(Camera camera, Map map) {
        if (map == null)
            return;
        OrthographicCamera orthographicCamera = (OrthographicCamera) camera;
        map.getMapRenderer().setView(orthographicCamera);
    }

    public GameEntity getEntityToFollow() {
        return entityToFollow;
    }

    public void setEntityToFollow(GameEntity entityToFollow) {
        this.entityToFollow = entityToFollow;
    }

    public boolean isBoundsEnabled() {
        return boundsEnabled;
    }

    public void setBoundsEnabled(boolean boundsEnabled) {
        this.boundsEnabled = boundsEnabled;
    }

    public float getLerp() {
        return lerp;
    }

    public void setLerp(float lerp) {
        this.lerp = lerp;
    }
}
