package com.zeju.todolist.service;


import com.zeju.todolist.model.Todo;
import com.zeju.todolist.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        given(todoRepository.findAll()).willReturn(fetchedTodos);

        //when
        List<Todo> returnedTodos = todoService.getAll();

        //then
        assertNotNull(returnedTodos);
        assertEquals(returnedTodos.size(), fetchedTodos.size());
    }

    @Test
    void should_return_updated_todo_when_update_todo_given_todo_and_id() {
        //given
        Integer id = 1;
        Optional<Todo> todo = Optional.of(new Todo(1, "todo1", true));
        Todo updateTodo = new Todo(1, "todo1", false);

        given(todoRepository.findById(id)).willReturn(todo);
        given(todoRepository.save(updateTodo)).willReturn(updateTodo);

        //when
        Todo returnedTodo = todoService.updateTodo(updateTodo,id);

        //then
        assertNotNull(returnedTodo);
    }

    @Test
    void should_return_todo_when_delete_todo_given_todo_id() {
        //given
        Integer id = 1;
        Optional<Todo> todo = Optional.of(new Todo(1, "todo1", false));
        given(todoRepository.findById(id)).willReturn(todo);
        todoRepository.deleteById(todo.get().getId());

        //when
        Todo deletedTodo = todoService.deleteTodo(id);

        //then
        assertNotNull(deletedTodo);
        assertEquals(id, deletedTodo.getId());
    }

    @Test
    void should_return_todo_when_add_todo_given_todo() {
        //given
        Todo todo = new Todo(null, "todo1", false);
        given(todoRepository.save(todo)).willReturn(new Todo(1, "todo1", false));

        //when
        Todo addedTodo = todoService.addTodo(todo);

        //then
        assertNotNull(addedTodo);
    }
}
