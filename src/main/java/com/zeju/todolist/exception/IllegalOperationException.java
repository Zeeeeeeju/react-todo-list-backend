package com.zeju.todolist.exception;

public class IllegalOperationException extends RuntimeException {

    public IllegalOperationException(){

    }

    public IllegalOperationException(String message){
        super(message);
    }

}
