package com.zeju.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoRequest implements Serializable {

    private String content;
    private Boolean status = false;

}
