package com.julianjupiter.storee.product.exception;

public class Message {
    private final String message;

    private Message(String message) {
        this.message = message;
    }

    public static Message of(String message) {
        return new Message(message);
    }

    public String getMessage() {
        return message;
    }
}
