package com.zeju.todolist.integration;

import com.alibaba.fastjson.JSONObject;
import com.zeju.todolist.dto.TodoRequest;
import com.zeju.todolist.repository.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
    void should_return_todoResponse_when_add_todo_given_todoRequest() throws Exception {
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
}
