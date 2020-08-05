package com.zeju.todolist.controller;

import com.zeju.todolist.dto.TodoRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class ToDoController {

    @GetMapping
    public List<TodoRequest> getAll(){
        return null;
    }

}
