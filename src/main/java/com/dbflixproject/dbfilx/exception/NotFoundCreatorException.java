package com.dbflixproject.dbfilx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST)
public class NotFoundCreatorException extends RuntimeException{
    public NotFoundCreatorException(){
        super("영화인 번호 오류");
    }
}
