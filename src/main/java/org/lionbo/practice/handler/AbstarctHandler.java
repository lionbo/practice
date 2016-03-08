package org.lionbo.practice.handler;

public abstract class AbstarctHandler implements IHandler {

    abstract public void beforehandle();

    abstract public void handle();

    abstract public void afterhandle();

    public void dohandle() {
        try {
            beforehandle();
            handle();
        } catch (Exception e) {
            //            logerror(e);

        }

        finally {
            afterhandle();
        }
    }

}
