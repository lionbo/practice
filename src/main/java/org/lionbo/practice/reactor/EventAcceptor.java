package org.lionbo.practice.reactor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EventAcceptor {

    private BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<Event>(100);

    public void addEvent(Event event) {
        eventQueue.add(event);
    }

    public Event getEvent() {
        try {
            return eventQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
