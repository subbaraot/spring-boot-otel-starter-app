package com.demo.todo.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.todo.api.models.TodoItem;

public interface TodoItemsRepository extends MongoRepository<TodoItem, String> {

}
