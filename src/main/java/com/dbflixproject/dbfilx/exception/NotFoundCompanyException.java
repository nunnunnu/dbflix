package com.dbflixproject.dbfilx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST)
public class NotFoundCompanyException extends RuntimeException{
    public NotFoundCompanyException(){super("제작사 번호 오류");}
}
