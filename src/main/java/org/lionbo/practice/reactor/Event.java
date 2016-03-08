package org.lionbo.practice.reactor;

public class Event {

    private int eventType;
    private String eventContent;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    @Override
    public String toString() {
        return "Event [eventType=" + eventType + ", eventContent=" + eventContent + "]";
    }

}
