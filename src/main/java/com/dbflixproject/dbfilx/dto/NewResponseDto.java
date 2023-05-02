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
public class NewResponseDto {
    private String message;
    private LocalDateTime time;
    @JsonIgnore
    private HttpStatus code;

    public NewResponseDto(String message, HttpStatus code){
        this.time = LocalDateTime.now();
        this.message = message;
        this.code = code;
    }
}
