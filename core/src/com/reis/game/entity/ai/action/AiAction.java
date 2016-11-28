package com.reis.game.entity.ai.action;

import com.reis.game.entity.ai.AI;

/**
 * Created by bernardoreis on 11/26/16.
 */

public class AiAction {

    public String name;
    public int priority;
    public boolean finished;
    public boolean paused;
    public float duration;
    private float elapsedTime;

    public AiAction (int priority, String name) {
        this (priority, name, -1);
    }

    public AiAction(int priority, String name, float duration)  {
        this.name = name;
        this.priority = priority;
        this.duration = duration;
        this.finished = false;
        this.paused = false;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void start(AI ai) {
        onStart(ai);
    }

    public void stop(AI ai) {
        this.finished = true;
    }

    public void update(AI ai, float delta) {
        if (paused)
            return;
        if (duration > -1)
            checkFinished(ai, delta);
        if (!finished)
            onUpdate(ai, delta);
    }

    private void checkFinished(AI ai, float delta) {
        this.elapsedTime += delta;
        if (this.elapsedTime > duration)
            finished = true;
    }

    public void onStart(AI ai) {

    }

    public void onStop(AI ai) {

    }

    public void onUpdate(AI ai, float delta) {

    }

    public void onComplete(AI ai) {

    }
}
