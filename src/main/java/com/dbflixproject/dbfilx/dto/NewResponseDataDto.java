package com.dbflixproject.dbfilx.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewResponseDataDto<T> extends NewResponseDto{
    private T data;

    public NewResponseDataDto(String message, HttpStatus code, T data){
        super(message, code);
        this.data = data;
    }

    public static <T> NewResponseDataDto<T> success(String message, T data) {
        return new NewResponseDataDto<>(message, HttpStatus.OK, data);
    }
    public static <T> NewResponseDataDto<T> fail(String message, T data) {
        return new NewResponseDataDto<>(message, HttpStatus.BAD_REQUEST, data);
    }
}
