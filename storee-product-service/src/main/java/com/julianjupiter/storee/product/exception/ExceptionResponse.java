package com.julianjupiter.storee.product.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;

public class ExceptionResponse {
    private final int status;
    private final String message;
    private final OffsetDateTime createdAt;
    private final URI path;
    @JsonProperty("errors")
    private final List<Message> messages;

    private ExceptionResponse(HttpStatus httpStatus, OffsetDateTime createdAt, URI path, List<Message> messages) {
        this.status = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
        this.createdAt = createdAt;
        this.path = path;
        this.messages = messages;
    }

    public static ExceptionResponse of(HttpStatus httpStatus, OffsetDateTime createdAt, URI path, List<Message> messages) {
        return new ExceptionResponse(httpStatus, createdAt, path, messages);
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public URI getPath() {
        return path;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
