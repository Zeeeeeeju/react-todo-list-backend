package com.zeju.todolist.service;


import com.zeju.todolist.model.Todo;
import com.zeju.todolist.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

public class TodoServiceTest {

    private final TodoRepository todoRepository = Mockito.mock(TodoRepository.class);
    private TodoService todoService = new TodoService(todoRepository);

    @Test
    void should_return_todos_when_get_all_given_none() {
        //given
        List<Todo> fetchedTodos = new ArrayList<>();
        fetchedTodos.add(new Todo(1, "todo1", false));
        given(todoRepository.getAll()).willReturn(fetchedTodos);

        //when
        List<Todo> returnedTodos = todoService.getAll();

        //then
        assertNotNull(returnedTodos);
        assertEquals(returnedTodos.size(), fetchedTodos.size());
    }

    @Test
    void should_return_updated_todo_when_update_todo_given_todo() {
        //given
        Todo todo = new Todo(1, "todo1", false);
        Todo updatedTodo = new Todo(1,"todo1",true);
        given(todoRepository.findTodoById(todo.getId())).willReturn(todo);
        given(todoRepository.save(updatedTodo)).willReturn(todo);

        //when
        Todo returnedTodo = todoService.updateTodo(todo);

        //then
        assertNotNull(returnedTodo);

    }
}
