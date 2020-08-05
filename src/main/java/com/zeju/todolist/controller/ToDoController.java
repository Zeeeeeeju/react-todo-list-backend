package com.zeju.todolist.controller;

import com.zeju.todolist.dto.TodoRequest;
import com.zeju.todolist.model.Todo;
import com.zeju.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class ToDoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> getAll() {
        return todoService.getAll();
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@RequestBody Todo todo, @PathVariable Integer id) {
        return todoService.updateTodo(todo, id);
    }

    @DeleteMapping("/{id}")
    public Todo deleteTodo(@PathVariable Integer id) {
        return todoService.deleteTodo(id);
    }

    @PostMapping
    public Todo updateTodo(@RequestBody Todo todo) {
        return todoService.addTodo(todo);
    }

}
