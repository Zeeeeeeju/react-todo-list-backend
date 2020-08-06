package com.zeju.todolist.service;


import com.zeju.todolist.exception.IllegalOperationException;
import com.zeju.todolist.exception.NoSuchDataException;
import com.zeju.todolist.model.Todo;
import com.zeju.todolist.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
        Todo returnedTodo = todoService.updateTodo(updateTodo, id);

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

    @Test
    void should_throw_IllegalOperationException_when_update_todo_given_wrong_todo_and_todo_id() {
        //given
        Integer todo_id = 1;
        Optional<Todo> todo = Optional.of(new Todo(1, "todo1", true));
        Todo updateTodo = new Todo(2, "todo1", false);

        given(todoRepository.findById(todo_id)).willReturn(todo);

        //when
        Throwable throwable = assertThrows(IllegalOperationException.class, () -> todoService.updateTodo(updateTodo, todo_id));

        //then
        assertEquals(IllegalOperationException.class, throwable.getClass());
    }

    @Test
    void should_throw_NoSuchDataException_when_update_todo_given_wrong_todo_id() {
        //given
        Integer todo_id = 99;
        Optional<Todo> todo = Optional.ofNullable(null);
        Todo updateTodo = new Todo(2, "todo1", false);

        given(todoRepository.findById(todo_id)).willReturn(todo);

        //when
        Throwable throwable = assertThrows(NoSuchDataException.class, () -> todoService.updateTodo(updateTodo, todo_id));

        //then
        assertEquals(NoSuchDataException.class, throwable.getClass());
    }

    @Test
    void should_throw_NoSuchDataException_when_delete_todo_given_wrong_todo_id() {
        //given
        Integer todo_id = 99;
        Optional<Todo> todo = Optional.ofNullable(null);

        given(todoRepository.findById(todo_id)).willReturn(todo);

        //when
        Throwable throwable = assertThrows(NoSuchDataException.class, () -> todoService.deleteTodo(todo_id));

        //then
        assertEquals(NoSuchDataException.class, throwable.getClass());
    }
}
