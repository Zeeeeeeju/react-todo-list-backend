package com.zeju.todolist.mapper;

import com.zeju.todolist.dto.TodoRequest;
import com.zeju.todolist.dto.TodoResponse;
import com.zeju.todolist.model.Todo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {

    public Todo convertTodoRequestToEntity(TodoRequest todoRequest) {
        Todo todo = new Todo();
        BeanUtils.copyProperties(todoRequest, todo);
        return todo;
    }

    public TodoResponse convertEntityToTodoResponse(Todo todo) {
        TodoResponse response = new TodoResponse();
        BeanUtils.copyProperties(todo, response);
        return response;
    }

}
