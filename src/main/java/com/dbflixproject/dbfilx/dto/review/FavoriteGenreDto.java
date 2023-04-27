package com.dbflixproject.dbfilx.dto.review;

import com.dbflixproject.dbfilx.entity.enumfile.MovieGenre;

public interface FavoriteGenreDto {
    MovieGenre getGenre();
    Double getRate();
}
