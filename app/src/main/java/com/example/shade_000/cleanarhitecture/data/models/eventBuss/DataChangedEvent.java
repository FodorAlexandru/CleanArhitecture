package com.example.shade_000.cleanarhitecture.data.models.eventBuss;

/**
 * Created by shade_000 on 3/27/2016.
 */
public class DataChangedEvent {
    private final String message;

    public DataChangedEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
