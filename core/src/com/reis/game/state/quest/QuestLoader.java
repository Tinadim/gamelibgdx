package com.reis.game.state.quest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bernardoreis on 11/18/16.
 */

public final class QuestLoader {

    private QuestLoader() {

    }

    public static Map<Integer, Quest> loadQuestLog() {
        return new HashMap<Integer, Quest>();
    }
}
