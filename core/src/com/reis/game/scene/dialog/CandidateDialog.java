package com.reis.game.scene.dialog;

import com.reis.game.state.quest.Quest;
import com.reis.game.state.quest.QuestManager;
import com.reis.game.util.CandidateResource;

/**
 * Created by bernardoreis on 11/19/16.
 */

public class CandidateDialog implements CandidateResource {

    public int resourceId;
    public Integer requiredQuestId;
    public Integer requiredStep;
    public String content;


    public CandidateDialog(int dialogId, Integer requiredQuestId, Integer requiredStep, String content) {
        this.resourceId = dialogId;
        this.requiredQuestId = requiredQuestId;
        this.requiredStep = requiredStep;
        this.content = content;
    }

    @Override
    public CandidateResource compareTo(CandidateResource otherResource) {
        CandidateDialog otherDialog = (CandidateDialog) otherResource;
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
        return content;
    }
}
