package com.reis.game.state.quest;

import com.reis.game.state.event.Event;
import com.reis.game.state.event.EventType;
import com.reis.game.state.event.reaction.Reaction;
import com.reis.game.state.requirement.Requirement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bernardoreis on 11/17/16.
 */

public class QuestStep {

    private List<Requirement> requirements;
    private List<Reaction> reactions;
    private boolean complete;

    public void addRequirement(Requirement requirement) {
        if(requirements == null)
            requirements = new ArrayList<Requirement>();
        this.requirements.add(requirement);
    }

    public void addReaction(Reaction reaction) {
        if(reactions == null)
            reactions = new ArrayList<Reaction>();
        this.reactions.add(reaction);
    }

    public void updateRequirements(Event event) {
        this.complete = true;
        for(Requirement requirement : requirements) {
            if(!requirement.checkEventType(event))
                break;
            requirement.update(event);
            complete = complete && requirement.check();
        }
    }

    public void onComplete() {
        for(Reaction reaction : reactions)
            reaction.fire();
        new Event(EventType.STEP_COMPLETE, this).fire();
    }

    public boolean isComplete() {
        return complete;
    }

}
