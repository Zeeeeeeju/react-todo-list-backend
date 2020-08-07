package com.zeju.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoRequest implements Serializable {

    private Integer id;

    @NotBlank(message = "Todo content must not blank")
    private String content;
    private Boolean status = false;

}
