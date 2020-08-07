package com.zeju.todolist.config;

import com.zeju.todolist.exception.IllegalOperationException;
import com.zeju.todolist.exception.NoSuchDataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionConfig {

    public static final String CAN_NOT_FIND_DATA = "Can not find data";
    public static final String ILLEGAL_OPERATION = "Illegal operation!";

    @ExceptionHandler(NoSuchDataException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String noSuchDataExceptionHandler() {
        return CAN_NOT_FIND_DATA;
    }

    @ExceptionHandler(IllegalOperationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String illegalOperationExceptionHandler() {
        return ILLEGAL_OPERATION;
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidException(MethodArgumentNotValidException e){
        return Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
    }

}
