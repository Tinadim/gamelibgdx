package com.reis.game.entity;

import com.reis.game.entity.player.Player;
import com.reis.game.entity.template.EntityTemplate;
import com.reis.game.resource.prototype.EntityTypeProto.EntityData;

/**
 * Created by bernardoreis on 11/24/16.
 */

public final class EntityFactory {

    private EntityFactory() {

    }

    public static GameEntity createFromTemplate(EntityTemplate template, EntityData data) {
        GameEntity entity = new GameEntity(data.getId(), data.getRow(), data.getCol());
        template.applyToEntity(entity, data);
        return entity;
    }

    public static Player createPlayer(EntityTemplate template, EntityData data) {
        Player player = new Player(data.getId(), data.getRow(), data.getCol());
        template.applyToEntity(player, data);
        return player;
    }
}
