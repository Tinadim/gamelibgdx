package com.reis.game.state.event;

/**
 * Created by bernardoreis on 11/17/16.
 */

public class Event {

    public EventType type;
    public Object trigger;

    public Event(EventType type, Object trigger) {
        this.trigger = trigger;
        this.type = type;
    }

    public void fire() {
        EventHandler.handleEvent(this);
    }
}
