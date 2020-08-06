package com.zeju.todolist.dto;

import lombok.Data;

@Data
public class TodoResponse {

    private Integer id;
    private String content;
    private Boolean status;

}
