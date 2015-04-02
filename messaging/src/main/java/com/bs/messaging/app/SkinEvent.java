package com.bs.messaging.app;

public class SkinEvent extends JsonMessage {
    private String text;

    public SkinEvent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "HelloMessage{" +
                "text='" + text + '\'' +
                '}';
    }
}

