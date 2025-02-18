package com.demo.todo.api.requests;

public class TodoItemCreateRequest {
    private String value;

    public TodoItemCreateRequest(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}