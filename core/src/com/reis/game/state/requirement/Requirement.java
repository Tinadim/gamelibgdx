package com.reis.game.state.requirement;

import com.reis.game.state.event.Event;
import com.reis.game.state.event.EventType;

/**
 * Created by bernardoreis on 11/17/16.
 */

public abstract class Requirement {

    protected EventType acceptedEventType;
    protected boolean fulfilled;

    public Requirement(EventType acceptedEventType) {
        this.acceptedEventType = acceptedEventType;
    }

    public boolean checkEventType(Event event) {
        return event.type.equals(acceptedEventType);
    }

    public boolean check() {
        return fulfilled;
    }

    public abstract void update(Event event);
}
