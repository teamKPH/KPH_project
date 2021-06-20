package com.teamkph.kph.advice.customException;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExceptionResponse {
    private boolean isSuccess = false;
    private String message;

    public ExceptionResponse(){}
    public ExceptionResponse(String message){this.message = message;}
}
