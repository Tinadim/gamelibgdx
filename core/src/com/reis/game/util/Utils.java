package com.reis.game.util;

import com.reis.game.resource.ResourceManager;

/**
 * Created by bernardoreis on 11/26/16.
 */

public final class Utils {

    private Utils() {

    }

    public static int randomInt(int min, int max) {
        return ResourceManager.random.nextInt((max - min) + 1) + min;
    }
}
