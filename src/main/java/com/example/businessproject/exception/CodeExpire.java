package com.example.businessproject.exception;

public class CodeExpire extends RuntimeException{
    public CodeExpire (String message){
        super(message);
    }
}
