package com.reis.game.state.quest;

import com.reis.game.state.GameState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by bernardoreis on 11/17/16.
 */

public final class QuestManager {

    private static Map<Integer, Quest> questLog;
    private static List<Quest> onGoingQuests = new ArrayList<Quest>();

    private QuestManager() {}


    public static void storeQuestStates(GameState state) {
        // TODO implement this
    }

    public static void loadQuests(GameState state) {
        questLog = QuestLoader.loadQuestLog();
        state.questStates = new HashMap<Integer, Integer>();
        state.questStates.put(1, 0);
        loadQuestStates(state);
    }

    private static void loadQuestStates(GameState gameState) {
        Map<Integer, Integer> questStates = gameState.questStates;
        Iterator<Map.Entry<Integer, Integer>> it = questStates.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<Integer, Integer> entry = it.next();
            Integer questStep = entry.getValue();
            Quest quest = questLog.get(entry.getKey());
            quest.setCurrentStepIndex(questStep);

            if (questStep >= Quest.NOT_STARTED) {
                onGoingQuests.add(quest);
            }
        }
    }

    public static int getQuestState(int questId) {
        return questLog.get(questId).getCurrentStepIndex();
    }

    public static List<Quest> getOngoingQuests() {
        return onGoingQuests;
    }

}
