package org.lionbo.practice.handler;

public class HandlerB extends AbstarctHandler {

    @Override
    public void beforehandle() {
        System.out.println("rengong other things before really start");
    }

    @Override
    public void handle() {
        System.out.println("rengong");
    }

    @Override
    public void afterhandle() {
        System.out.println("rengong other things before really end");
    }

}
