package com.reis.game.state.quest;

import com.reis.game.resource.prototype.QuestLogProto;
import com.reis.game.state.event.Event;
import com.reis.game.state.event.reaction.DialogReaction;
import com.reis.game.state.requirement.EntityInteractionRequirement;
import com.reis.game.state.requirement.Requirement;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bernardoreis on 11/18/16.
 */

public final class QuestLoader {

    private QuestLoader() {

    }

    // TODO properly implement this

    public static Map<Integer, Quest> loadQuestLog() {
        return loadMockQuestLog();
    }

    private static Map<Integer, Quest> loadMockQuestLog() {
        Map<Integer, Quest> questLog = new HashMap<Integer, Quest>();
        Quest quest = new Quest(1);

        QuestStep step1 = new QuestStep(1);
        EntityInteractionRequirement requirement1 = new EntityInteractionRequirement(2);
        DialogReaction reaction1 = new DialogReaction(2);
        step1.addRequirement(requirement1);
        step1.addReaction(reaction1);

        QuestStep step2 = new QuestStep(2);
        EntityInteractionRequirement requirement2 = new EntityInteractionRequirement(3);
        DialogReaction reaction2 = new DialogReaction(3);
        step2.addRequirement(requirement2);
        step2.addReaction(reaction2);

        QuestStep step3 = new QuestStep(3);
        EntityInteractionRequirement requirement3 = new EntityInteractionRequirement(2);
        DialogReaction reaction3 = new DialogReaction(2);
        step3.addRequirement(requirement3);
        step3.addReaction(reaction3);

        quest.addStep(step1);
        quest.addStep(step2);
        quest.addStep(step3);

        questLog.put(1, quest);
        return questLog;
    }
}
