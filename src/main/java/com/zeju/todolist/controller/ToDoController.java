package com.zeju.todolist.controller;

import com.zeju.todolist.dto.TodoRequest;
import com.zeju.todolist.dto.TodoResponse;
import com.zeju.todolist.mapper.TodoMapper;
import com.zeju.todolist.model.Todo;
import com.zeju.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class ToDoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoMapper todoMapper;

    @GetMapping
    public List<Todo> getAll() {
        return todoService.getAll();
    }

    @PutMapping("/{id}")
    public TodoResponse updateTodo(@RequestBody TodoRequest todo, @PathVariable Integer id) {
        return todoMapper.convertEntityToTodoResponse(todoService.updateTodo(todoMapper.convertTodoRequestToEntity(todo), id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Todo deleteTodo(@PathVariable Integer id) {
        return todoService.deleteTodo(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo updateTodo(@RequestBody Todo todo) {
        return todoService.addTodo(todo);
    }

}
