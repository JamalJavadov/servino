package com.example.businessproject.exception;

public class VerifyCodeFailedException extends RuntimeException{
    public VerifyCodeFailedException(String message){
        super(message);
    }
}
