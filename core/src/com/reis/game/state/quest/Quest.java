package com.reis.game.state.quest;

import com.reis.game.state.event.Event;
import com.reis.game.state.event.EventType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bernardoreis on 11/17/16.
 */

public class Quest {

    public static final int FINISHED = -1;
    public static final int NOT_STARTED = 0;

    private int id;
    private int currentStepIndex;

    public List<QuestStep> steps;

    public Quest(int id) {
        this(id, NOT_STARTED);
    }

    public Quest(int id, int currentStepIndex) {
        this.id = id;
        this.currentStepIndex = currentStepIndex;
        this.steps = new ArrayList<QuestStep>();
    }

    public void addStep(QuestStep step) {
        this.steps.add(step);
    }

    public int getCurrentStepIndex() {
        return this.currentStepIndex;
    }

    public void setCurrentStepIndex(int stepIndex) {
        this.currentStepIndex = stepIndex;
    }

    public void updateCurrentStep(Event event) {
        QuestStep currentStep = this.steps.get(currentStepIndex);
        currentStep.updateRequirements(event);
        if(currentStep.isComplete()) {
            this.currentStepIndex++;
            this.onStepComplete(currentStep);
        }
    }

    public void onStepComplete(QuestStep step) {
        if(currentStepIndex == steps.size()) {
            QuestManager.questFinished(this);
            this.onQuestComplete();
        }
        step.onComplete();
    }

    public void onQuestComplete(){
        new Event(EventType.QUEST_COMPLETE, this).fire();
    }
}
