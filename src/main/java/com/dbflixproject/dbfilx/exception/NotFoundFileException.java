package com.dbflixproject.dbfilx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST)
public class NotFoundFileException extends RuntimeException{
    public NotFoundFileException(){
        super("존재하는 파일이 없습니다.");
    }
}
