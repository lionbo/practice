package org.lionbo.practice.reactor;

public abstract class EventHandler {

    private int eventType;

    public EventHandler(int eventType) {
        this.eventType = eventType;
    }

    public abstract void doSomethingWithEvent();

}
