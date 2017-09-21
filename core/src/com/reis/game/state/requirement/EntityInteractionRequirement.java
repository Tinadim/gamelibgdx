package com.reis.game.state.requirement;

import com.reis.game.entity.GameEntity;
import com.reis.game.state.event.Event;
import com.reis.game.state.event.EventType;

/**
 * Created by bernardoreis on 9/2/17.
 */

public class EntityInteractionRequirement extends Requirement {

    private int entityId;

    public EntityInteractionRequirement(int entityId) {
        super(EventType.ENTITY_INTERACTION);
        this.entityId = entityId;
    }

    @Override
    public void update(Event event) {
        if (!(event.trigger instanceof GameEntity))
            return;
        if (((GameEntity) event.trigger).id == entityId)
            this.fulfilled = true;
    }

}