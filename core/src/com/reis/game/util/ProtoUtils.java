package com.reis.game.util;

import com.badlogic.gdx.math.Vector2;
import com.reis.game.resource.prototype.AI.AIData;
import com.reis.game.resource.prototype.AI.Waypoint;

import java.util.List;

/**
 * Created by bernardoreis on 11/27/16.
 */

public final class ProtoUtils {

    private ProtoUtils() {}

    public static Vector2[] extractWayPoints(AIData data) {
        List<Waypoint> waypointList = data.getWaypointList();
        if (waypointList == null)
            return null;
        Vector2[] waypoints = new Vector2[waypointList.size()];
        for (int i = 0; i < waypointList.size(); i++) {
            Waypoint waypoint = waypointList.get(i);
            waypoints[i] = MapUtils.getCoordsForTile(waypoint.getX(), waypoint.getY());
        }
        return waypoints;
    }
}
