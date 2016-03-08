package org.lionbo.practice.reactor;

import java.util.concurrent.ConcurrentHashMap;

public class Reactor {

    private EventAcceptor acceptor;
    private ConcurrentHashMap<Integer, EventHandler> handlerMap;

    public Reactor(EventAcceptor acceptor) {
        this.acceptor = acceptor;
    }

    public void handleEvent() {
        System.out.println(acceptor.getEvent());
    }

}
