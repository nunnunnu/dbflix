package com.dbflixproject.dbfilx.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto<T> {
    private String message;
    private LocalDateTime time;
    private Boolean status;
    private T data;
    @JsonIgnore
    private HttpStatus code;
    private ResponseDto(SuccessBuilder<T> builder){
        this.time = builder.time;
        this.code = builder.code;
        this.status = builder.status;
        this.message = builder.message;
        this.data = builder.data;
    }
    private ResponseDto(FailBuilder<T> builder){
        this.time = builder.time;
        this.code = builder.code;
        this.status = builder.status;
        this.message = builder.message;
        this.data = builder.data;
    }

    public static class SuccessBuilder<T>{
        private String message;
        private T data;

        private LocalDateTime time = LocalDateTime.now();
        private Boolean status = true;
        private HttpStatus code = HttpStatus.OK;

        public SuccessBuilder(String message, T data){
            this.message = message;
            this.data = data;
        }

        public SuccessBuilder<T> time(LocalDateTime time){
            this.time = time;
            return this;
        }
        public SuccessBuilder<T> status(Boolean status){
            this.status = status;
            return this;
        }
        public SuccessBuilder<T> code(HttpStatus code){
            this.code = code;
            return this;
        }

        public ResponseDto<T> build() {
            return new ResponseDto<T>(this);
        }

    }
    public static class FailBuilder<T>{
        private String message;
        private T data;

        private LocalDateTime time = LocalDateTime.now();
        private Boolean status = false;
        private HttpStatus code = HttpStatus.BAD_REQUEST;

        public FailBuilder(String message){
            this.message = message;
            this.data = null;
        }

        public FailBuilder<T> time(LocalDateTime time){
            this.time = time;
            return this;
        }
        public FailBuilder<T> status(Boolean status){
            this.status = status;
            return this;
        }
        public FailBuilder<T> code(HttpStatus code){
            this.code = code;
            return this;
        }

        public ResponseDto<T> build() {
            return new ResponseDto<T>(this);
        }

    }
}
