package com.reis.game.scene.dialog;

import com.reis.game.state.quest.Quest;
import com.reis.game.state.quest.QuestManager;
import com.reis.game.util.CandidateResource;

/**
 * Created by bernardoreis on 11/19/16.
 */

public class Dialog implements CandidateResource {

    public int resourceId;
    public Integer requiredQuestId;
    public Integer requiredStep;


    public Dialog(int dialogId, Integer requiredQuestId, Integer requiredStep) {
        this.resourceId = dialogId;
        this.requiredQuestId = requiredQuestId;
        this.requiredStep = requiredStep;
    }

    @Override
    public CandidateResource compareTo(CandidateResource otherResource) {
        Dialog otherDialog = (Dialog) otherResource;
        if(this.requiredQuestId == null)
            return otherDialog;
        if(otherDialog.requiredQuestId == null)
            return this;
        if(otherDialog.requiredQuestId > requiredQuestId)
            return otherDialog;
        if(otherDialog.requiredQuestId == requiredQuestId)
            return (otherDialog.requiredStep >= requiredStep) ? otherDialog : this;
        return this;
    }

    @Override
    public boolean matchesRequirements() {
        if(requiredQuestId == null)
            return true;
        int questState = QuestManager.getQuestState(requiredQuestId);
        if(questState == Quest.FINISHED)
            return true;
        return questState >= requiredStep;
    }

    public String getContent() {
        return "";
    }
}
