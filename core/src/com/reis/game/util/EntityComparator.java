package com.reis.game.util;

import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Comparator;

/**
 * Created by bernardoreis on 11/20/16.
 */

public class EntityComparator implements Comparator<Actor> {

    @Override
    public int compare(Actor a1, Actor a2) {
        return (int) (a1.getY() - a2.getY());
    }
}
