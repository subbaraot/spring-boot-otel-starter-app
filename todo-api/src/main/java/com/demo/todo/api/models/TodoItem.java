package com.demo.todo.api.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Items")
public class TodoItem {
    private String id;
    private String value;
    private boolean isDone;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return String.format("Id: %s; Value: %s; Done: %s", id, value, isDone);
    }
}
