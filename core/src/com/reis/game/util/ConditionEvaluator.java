package com.reis.game.util;

import java.util.List;

/**
 * Created by bernardoreis on 11/26/16.
 */

public final class ConditionEvaluator {

    private ConditionEvaluator() {}

    public static boolean areConditionsFulfilled(List<? extends Condition> conditions) {
        for (Condition condition : conditions) {
            boolean conditionFulfilled = condition.avaliate();
            if (!conditionFulfilled)
                return false;
        }
        return true;
    }
}
