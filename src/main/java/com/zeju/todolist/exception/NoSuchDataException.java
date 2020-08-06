package com.zeju.todolist.exception;

public class NoSuchDataException extends RuntimeException{

    public NoSuchDataException(){}

    public NoSuchDataException(String message){
        super(message);
    }

}
