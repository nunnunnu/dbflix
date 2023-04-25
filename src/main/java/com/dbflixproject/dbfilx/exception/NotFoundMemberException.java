package com.dbflixproject.dbfilx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST)
public class NotFoundMemberException extends RuntimeException{
    public NotFoundMemberException() {
        super("회원번호 오류");
    }
}
