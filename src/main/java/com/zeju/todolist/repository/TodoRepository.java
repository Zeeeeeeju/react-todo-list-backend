package com.zeju.todolist.repository;

import com.zeju.todolist.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository {
    List<Todo> getAll();

    Todo save(Todo todo);

    Todo findTodoById(Integer id);

    Todo deleteById(Integer id);
}
