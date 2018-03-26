package com.reis.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.reis.game.resource.ResourceManager;
import com.reis.game.resource.prototype.AI.AIData;
import com.reis.game.resource.prototype.AI.Waypoint;
import com.reis.game.resource.prototype.AnimationProto;
import com.reis.game.resource.prototype.EntityTypeProto.EntityData;
import com.reis.game.resource.prototype.ScreenProto;
import com.reis.game.resource.prototype.ScreenProto.ScreenData;
import com.reis.game.resource.prototype.AnimationProto.AnimationData;
import com.reis.game.resource.prototype.AnimationProto.AnimationPrototype;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
                    .addEntityData(buildMockNpc(2, 5, 5))
                    .addEntityData(buildMockNpc(3, 10, 10))
                    //.addEntityData(buildMockEnemy())
                    .build();

            data.writeTo(stream);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private EntityData buildMockNpc(int id, int row, int column) {

        AIData aiData = AIData
                .newBuilder()
                .addWaypoint(Waypoint.newBuilder().setX(column - 3).setY(row).build())
                .addWaypoint(Waypoint.newBuilder().setX(column).setY(row).build())
                .addWaypoint(Waypoint.newBuilder().setX(column + 3).setY(row).build())
                .addWaypoint(Waypoint.newBuilder().setX(column).setY(row).build())
                .build();

        AnimationData animationData = buildMockAnimationData();

        return EntityData
                .newBuilder()
                .setTemplateName("NpcTemplate")
                .setRow(row)
                .setCol(column)
                .setId(id)
                .setAiData(aiData)
                .setAnimationData(animationData)
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
                .setRow(15)
                .setCol(15)
                .setId(3)
                .setAiData(aiData)
                .build();
    }

    private AnimationData buildMockAnimationData() {
        AnimationPrototype ad0 = AnimationPrototype
                .newBuilder()
                .setActionClassName("MovementAction")
                .setEntityOrientation(0)
                .setAtlasName("female_villager.atlas")
                .setAnimationName("female_villager_0")
                .setFrameDuration(0.25f)
                .build();

        AnimationPrototype ad1 = AnimationPrototype
                .newBuilder()
                .setActionClassName("MovementAction")
                .setEntityOrientation(1)
                .setAtlasName("female_villager.atlas")
                .setAnimationName("female_villager_1")
                .setFrameDuration(0.25f)
                .build();

        AnimationPrototype ad2 = AnimationPrototype
                .newBuilder()
                .setActionClassName("MovementAction")
                .setEntityOrientation(2)
                .setAtlasName("female_villager.atlas")
                .setAnimationName("female_villager_2")
                .setFrameDuration(0.25f)
                .build();

        AnimationPrototype ad3 = AnimationPrototype
                .newBuilder()
                .setActionClassName("MovementAction")
                .setEntityOrientation(3)
                .setAtlasName("female_villager.atlas")
                .setAnimationName("female_villager_3")
                .setFrameDuration(0.25f)
                .build();

        AnimationPrototype ad4 = AnimationPrototype
                .newBuilder()
                .setActionClassName("IdleAction")
                .setEntityOrientation(0)
                .setAtlasName("female_villager.atlas")
                .setAnimationName("female_villager_0")
                .setFrameDuration(0.25f)
                .build();

        AnimationPrototype ad5 = AnimationPrototype
                .newBuilder()
                .setActionClassName("IdleAction")
                .setEntityOrientation(1)
                .setAtlasName("female_villager.atlas")
                .setAnimationName("female_villager_1")
                .setFrameDuration(0.25f)
                .build();

        AnimationPrototype ad6 = AnimationPrototype
                .newBuilder()
                .setActionClassName("IdleAction")
                .setEntityOrientation(2)
                .setAtlasName("female_villager.atlas")
                .setAnimationName("female_villager_2")
                .setFrameDuration(0.25f)
                .build();

        AnimationPrototype ad7 = AnimationPrototype
                .newBuilder()
                .setActionClassName("IdleAction")
                .setEntityOrientation(3)
                .setAtlasName("female_villager.atlas")
                .setAnimationName("female_villager_3")
                .setFrameDuration(0.25f)
                .build();

        ArrayList<AnimationPrototype> data = new ArrayList<AnimationPrototype>();
        data.add(ad0);
        data.add(ad1);
        data.add(ad2);
        data.add(ad3);
        data.add(ad4);
        data.add(ad5);
        data.add(ad6);
        data.add(ad7);

        AnimationData animationData = AnimationData
                .newBuilder()
                .addAllAnimationPrototype(data)
                .build();
        return animationData;
    }

}
