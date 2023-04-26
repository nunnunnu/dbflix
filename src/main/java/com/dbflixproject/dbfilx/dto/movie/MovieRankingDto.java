package com.dbflixproject.dbfilx.dto.movie;

import com.dbflixproject.dbfilx.entity.movie.MovieInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public interface MovieRankingDto {
    Long getSeq();
    String getName();
    Integer getAttendance();
    Double getRate();

}
