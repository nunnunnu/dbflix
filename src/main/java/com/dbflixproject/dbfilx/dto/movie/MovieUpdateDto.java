package com.dbflixproject.dbfilx.dto.movie;

import com.dbflixproject.dbfilx.entity.enumfile.MovieGenre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieUpdateDto {
    private String name;
    private Integer attendance;
    private LocalDate regDt;
    private Integer price;
    private String county;
    private MovieGenre genre;
}
