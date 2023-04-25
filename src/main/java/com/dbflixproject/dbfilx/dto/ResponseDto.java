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
}
