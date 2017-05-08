package com.reis.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.reis.game.resource.ResourceManager;
import com.reis.game.resource.prototype.AI.AIData;
import com.reis.game.resource.prototype.AI.Waypoint;
import com.reis.game.resource.prototype.EntityTypeProto.EntityData;
import com.reis.game.resource.prototype.ScreenProto;
import com.reis.game.resource.prototype.ScreenProto.ScreenData;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * Created by bernardoreis on 11/20/16.
 */

public class InitialState extends GameState {

    private String fileName = "scene1.data";

    public InitialState() {
        this.createMockSceneData();
        this.questStates = new HashMap<Integer, Integer>();
        this.mapName = fileName;
        this.playerData = EntityData
                .newBuilder()
                .setCol(0)
                .setRow(0)
                .setId(1)
                .build();
    }

    private void createMockSceneData() {
        try {
            FileHandle file = Gdx.files.internal(ResourceManager.SCREEN_DATA_PATH + fileName);
            OutputStream stream = new FileOutputStream(file.file());

            ScreenData data = ScreenProto.ScreenData
                    .newBuilder()
                    .setBackgroundMusicName("intro.mp3")
                    .setTileMapName("desert.tmx")
                    //.addEntityData(buildMockNpc())
                    //.addEntityData(buildMockEnemy())
                    .build();

            data.writeTo(stream);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private EntityData buildMockNpc() {

        AIData aiData = AIData
                .newBuilder()
                .addWaypoint(Waypoint.newBuilder().setX(2).setY(5).build())
                .addWaypoint(Waypoint.newBuilder().setX(5).setY(5).build())
                .addWaypoint(Waypoint.newBuilder().setX(8).setY(5).build())
                .addWaypoint(Waypoint.newBuilder().setX(5).setY(5).build())
                .build();

        return EntityData
                .newBuilder()
                .setTemplateName("NpcTemplate")
                .setRow(5)
                .setCol(5)
                .setId(2)
                .setAiData(aiData)
                .build();
    }

    private EntityData buildMockEnemy() {

        AIData aiData = AIData
                .newBuilder()
                .addWaypoint(Waypoint.newBuilder().setX(7).setY(10).build())
                .addWaypoint(Waypoint.newBuilder().setX(10).setY(10).build())
                .addWaypoint(Waypoint.newBuilder().setX(13).setY(10).build())
                .addWaypoint(Waypoint.newBuilder().setX(10).setY(10).build())
                .build();

        return EntityData
                .newBuilder()
                .setTemplateName("EnemyTemplate")
                .setRow(10)
                .setCol(10)
                .setId(3)
                .setAiData(aiData)
                .build();
    }

}
