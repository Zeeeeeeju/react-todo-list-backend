package com.zeju.todolist.service;

import com.zeju.todolist.exception.IllegalOperationException;
import com.zeju.todolist.exception.NoSuchDataException;
import com.zeju.todolist.model.Todo;
import com.zeju.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    public Todo updateTodo(Todo todo, Integer id) {
        Optional<Todo> returnedTodo = todoRepository.findById(id);
        if (!returnedTodo.isPresent()) {
            throw new NoSuchDataException();
        }
        if(todo.getId()!=id)
            throw new IllegalOperationException();
        return todoRepository.save(todo);
    }

    public Todo deleteTodo(Integer id) {
        Optional<Todo> returnedTodo = todoRepository.findById(id);
        if (!returnedTodo.isPresent()) {
            return null;
        }
        todoRepository.deleteById(returnedTodo.get().getId());
        return returnedTodo.get();
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }
}
