package Util;

import People.Human;

public class Message {
    private Human sender;
    private String content;

    public Message(Human sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    public Human getSender() {
        return sender;
    }

    public void setSender(Human sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
