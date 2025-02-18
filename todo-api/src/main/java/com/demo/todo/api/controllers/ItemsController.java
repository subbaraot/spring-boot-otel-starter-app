package com.demo.todo.api.controllers;

import java.util.*;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.demo.todo.api.models.TodoItem;
import com.demo.todo.api.repositories.TodoItemsRepository;
import com.demo.todo.api.requests.TodoItemCreateRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ItemsController {
    private TodoItemsRepository repository;
    private Logger logger = LoggerFactory.getLogger(ItemsController.class);

    public ItemsController(TodoItemsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/items")
    public List<TodoItem> getItems() {
        try {

            logger.trace("Entering getItems.");

            logger.info("Loading all todo items...");

            List<TodoItem> items = repository.findAll();

            logger.info("All todo items loaded.");

            logger.trace("Exiting getItems.");

            return items;

        } catch (Exception ex) {
            logger.error("Error executing getItems.", ex);

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unknown error.", ex);
        }
    }

    @GetMapping("/items/{id}")
    public TodoItem getItem(@PathVariable String id) {
        try {

            logger.trace("Entering getItem.");

            logger.info("Finding todo item with id {}...", id);

            Optional<TodoItem> item = repository.findById(id);

            if (item.isPresent()) {
                logger.trace("Exiting getItem.");

                return item.get();
            }

            logger.warn("Todo item with id {} is not found. Aborting!", id);

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        } catch (Exception ex) {
            logger.error("Error executing getItem.", ex);

            if (ex.getClass() == ResponseStatusException.class)
                throw ex;

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unknown error.", ex);
        }
    }

    @PostMapping("/items")
    public TodoItem saveItem(@RequestBody TodoItemCreateRequest request) {

        if (request.getValue().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        TodoItem item = new TodoItem();
        item.setId(UUID.randomUUID().toString());
        item.setValue(request.getValue());

        return repository.save(item);
    }

    @PutMapping("/items/{id}")
    public TodoItem putMethodName(@PathVariable String id, @RequestParam boolean isDone) {

        TodoItem item = getItem(id);

        logger.info("Making todo item with id {} as '{}''.", id, (isDone ? "Done" : "Not Done"));

        item.setIsDone(isDone);

        return repository.save(item);
    }
}
