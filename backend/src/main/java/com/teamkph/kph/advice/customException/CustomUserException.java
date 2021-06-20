package com.teamkph.kph.advice.customException;

public class CustomUserException extends RuntimeException{
    public CustomUserException(){
        super();
    }

    public CustomUserException(String message){
        super(message);
    }

    public CustomUserException(String message, Throwable cause){
        super(message, cause);
    }
}
