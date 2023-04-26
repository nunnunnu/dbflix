package com.dbflixproject.dbfilx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotFoundMovieException extends RuntimeException {
    public NotFoundMovieException(){
        super("영화 번호 오류");
    }
}
