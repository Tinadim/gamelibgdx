package com.reis.game.state;

import com.reis.game.resource.prototype.EntityTypeProto.EntityData;

import java.util.Map;

/**
 * Created by bernardoreis on 11/16/16.
 */

public class GameState {

    public String mapName;
    public Map<Integer, Integer> questStates;
    public EntityData playerData;

}
