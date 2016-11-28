package com.reis.game.entity.components;

import com.reis.game.entity.GameEntity;
import com.reis.game.entity.ai.AI;

/**
 * Created by bernardoreis on 11/24/16.
 */

public class AiComponent extends EntityComponent {

    private AI ai;

    public AiComponent(GameEntity entity, AI ai) {
        super(entity);
        this.ai = ai;
    }

    public AI getAi() {
        return ai;
    }

    @Override
    public void update(GameEntity entity, float delta) {
        this.ai.update(delta);
    }
}
