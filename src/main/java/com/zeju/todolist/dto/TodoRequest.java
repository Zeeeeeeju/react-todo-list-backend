package com.zeju.todolist.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TodoRequest implements Serializable {

    private String content;
    private Boolean status = false;

}
