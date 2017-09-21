package com.reis.game.contants;

/**
 * Created by bernardoreis on 11/26/16.
 */

public interface ActionConstants {

    public final static int PRIORITY_MIN = 0;
    public final static int PRIORITY_MAX = 10;

    public final static int IDLE_PRIORITY = PRIORITY_MIN;
    public final static int MOVE_PRIORITY = 5;
    public final static int ATTACK_PRIORITY = 8;
    public final static int KNOCKBACK_PRIORITY = 9;
    public final static int INTRACTION_PRIORITY = 9;
    public final static int DIE_PRIORITY = PRIORITY_MAX;

    public final static String IDLE_NAME = "idle";
    public final static String MOVE_NAME = "move";
    public final static String ATTACK_NAME = "attack";
    public final static String KNOCKBACK_NAME = "knockback";
    public final static String INTERACTION_NAME = "interaction";

    public static final float DEFAULT_SPEED = 200f;
    public static final float DEFAULT_RUN_SPEED = 2 * DEFAULT_SPEED;
    public static final float KNOCKBACK_RUN_SPEED = 4 * DEFAULT_SPEED;
    public static final float EPSILON = 0.001f;

    public static final float BASE_TURN_DURATION = 1;      //seconds
    public static final int DEFAULT_MIN_IDLE_TURNS = 1;
    public static final int DEFAULT_MAX_IDLE_TURNS = 4;
    public static final int DEFAULT_RANGE_OF_SIGHT = 5;
}
