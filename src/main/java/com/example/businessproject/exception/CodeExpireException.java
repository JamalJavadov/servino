package com.example.businessproject.exception;

public class CodeExpireException extends RuntimeException{
    public CodeExpireException(String message){
        super(message);
    }
}
