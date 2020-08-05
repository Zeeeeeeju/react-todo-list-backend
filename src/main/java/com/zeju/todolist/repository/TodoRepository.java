package com.zeju.todolist.repository;

import com.zeju.todolist.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository {
    List<Todo> getAll();
}
