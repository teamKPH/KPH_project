package com.teamkph.kph.advice;

import com.teamkph.kph.advice.customException.CustomUserException;
import com.teamkph.kph.advice.customException.CustomValidationException;
import com.teamkph.kph.advice.customException.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ExceptionResponse defaultException(HttpServletRequest req, Exception e){
        return new ExceptionResponse("Default Error");
    }

    @ExceptionHandler(CustomValidationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ExceptionResponse validationException(HttpServletRequest req, CustomValidationException e){
        if (e.getMessage() != null) {
            if (e.getMessage().equals("email")) return new ExceptionResponse("중복되는 이메일입니다.");
        }
        return new ExceptionResponse("잘 못 된 입력 값입니다.");
    }

    @ExceptionHandler(CustomUserException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ExceptionResponse userException(HttpServletRequest req, CustomUserException e){
        return new ExceptionResponse("잘 못 된 사용자 정보입니다.");
    }

}
