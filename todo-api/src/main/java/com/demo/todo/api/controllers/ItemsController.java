package com.demo.todo.api.controllers;

import java.util.*;

import org.springframework.web.bind.annotation.RestController;

import com.demo.todo.api.models.TodoItem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ItemsController {

    @GetMapping("/items")
    public List<TodoItem> getItems() {
        return new ArrayList<>();
    }

    @GetMapping("/items/{id}")
    public TodoItem getItem(@PathVariable String id) {
        return new TodoItem();
    }

}
