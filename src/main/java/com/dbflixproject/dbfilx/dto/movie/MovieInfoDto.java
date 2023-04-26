package com.dbflixproject.dbfilx.dto.movie;

import com.dbflixproject.dbfilx.entity.movie.MovieInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieInfoDto {
    private Long seq;
    private String name;

    public MovieInfoDto(MovieInfoEntity entity){
        this.seq = entity.getMiSeq();
        this.name = entity.getMiTitle();
    }
}
