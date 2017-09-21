package com.reis.game.state.event;

import com.reis.game.state.quest.Quest;
import com.reis.game.state.quest.QuestManager;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by bernardoreis on 11/17/16.
 */

public final class EventHandler {

    private EventHandler() {}

    public static void handleEvent(Event event) {
        List<Quest> ongoingQuests = QuestManager.getOngoingQuests();
        updateOngoingQuests(ongoingQuests, event);
    }

    private static void updateOngoingQuests(Collection<Quest> quests, Event event) {
        Iterator<Quest> questIterator = quests.iterator();
        while (questIterator.hasNext()) {
            Quest quest = questIterator.next();
            if (event.trigger == quest) {
                continue;
            }

            quest.updateCurrentStep(event);
            if (quest.isComplete()) {
                questIterator.remove();
            }
        }
    }

}
