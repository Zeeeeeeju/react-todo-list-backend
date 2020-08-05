package com.zeju.todolist.service;

import com.zeju.todolist.model.Todo;
import com.zeju.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAll(){
        return todoRepository.getAll();
    }

    public Todo updateTodo(Todo todo){
        Todo returnedTodo = todoRepository.findTodoById(todo.getId());
        if(returnedTodo == null){
            return null;
        }
        return todoRepository.save(returnedTodo);
    }

    public Todo deleteTodo(Todo todo) {
        Todo returnedTodo = todoRepository.findTodoById(todo.getId());
        if(returnedTodo == null){
            return null;
        }
        todoRepository.deleteById(returnedTodo.getId());
        return returnedTodo;
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }
}
