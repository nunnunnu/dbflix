package com.dbflixproject.dbfilx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotFoundEntityException extends RuntimeException{
    public NotFoundEntityException(){
    }
    public NotFoundEntityException(String entityType){
        super(entityType+" 번호 오류");
    }
}
