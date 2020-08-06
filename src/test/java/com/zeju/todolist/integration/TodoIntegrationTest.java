package com.zeju.todolist.integration;

import com.alibaba.fastjson.JSONObject;
import com.zeju.todolist.dto.TodoRequest;
import com.zeju.todolist.model.Todo;
import com.zeju.todolist.repository.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class TodoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    @AfterEach
    void tearDown() {
        todoRepository.deleteAll();
    }

    @Test
    void should_return_added_todo_when_add_todo_given_todo() throws Exception {
        //given
        TodoRequest todoRequest = new TodoRequest("abc",false);

        //when
        String todoJson = JSONObject.toJSONString(todoRequest);

        mockMvc.perform(post("/todos").contentType(MediaType.APPLICATION_JSON).content(todoJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.content").value(todoRequest.getContent()))
                .andExpect(jsonPath("$.status").value(todoRequest.getStatus()));
    }

    @Test
    void should_return_deleted_todo_when_delete_todo_given_todo_id() throws Exception {
        //given
        Todo todo = new Todo(null,"abc",false);
        Todo addedTodo = todoRepository.save(todo);

        //when
        mockMvc.perform(delete("/todos/"+addedTodo.getId()))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.content").value(addedTodo.getContent()))
                .andExpect(jsonPath("$.status").value(addedTodo.getStatus()));

    }
}
