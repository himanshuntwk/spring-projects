package com.himanshu.websocketclientserver.server;

public class OutgoingMessage {
    private String content;

    public OutgoingMessage(String content) {
        this.content = content;
    }

    public OutgoingMessage() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
