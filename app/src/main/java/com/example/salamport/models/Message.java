package com.example.salamport.models;

public class Message {
    private Long id;

    private String text;
    private long from;
    private long to;

    public Message(String text, long from, long to) {
        this.text = text;
        this.from = from;
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public long getFrom() {
        return from;
    }

    public long getTo() {
        return to;
    }

}
