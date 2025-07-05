package com.example.businessproject.exception;

public class UserNotFoundException extends NotFoundException{
    public UserNotFoundException(String message){
        super(message);
    }
}
