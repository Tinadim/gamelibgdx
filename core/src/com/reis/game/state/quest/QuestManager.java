package com.reis.game.state.quest;

import com.reis.game.state.GameState;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by bernardoreis on 11/17/16.
 */

public final class QuestManager {

    private static Map<Integer, Quest> questLog;
    private static List<Quest> onGoingQuests;

    private QuestManager() {
        onGoingQuests = new ArrayList<Quest>();
    }

    public static void storeQuestStates(GameState state) {

    }

    public static void loadQuests(GameState state) {
        questLog = QuestLoader.loadQuestLog();
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

            if(questStep > Quest.NOT_STARTED)
                onGoingQuests.add(quest);
        }
    }

    public static int getQuestState(int questId) {
        return questLog.get(questId).getCurrentStepIndex();
    }

    public static List<Quest> getOngoingQuests() {
        return onGoingQuests;
    }

    public static void questFinished(Quest quest) {
        onGoingQuests.remove(quest);
    }

}
