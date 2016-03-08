package org.lionbo.practice.handler;

public class HandlerA extends AbstarctHandler {

    @Override
    public void beforehandle() {
        System.out.println("lingyun log start");
    }

    @Override
    public void handle() {
        System.out.println("lingyun");
    }

    @Override
    public void afterhandle() {
        System.out.println("lingyun log end");
    }

}
