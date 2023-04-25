package com.dbflixproject.dbfilx.repository;

import com.dbflixproject.dbfilx.entity.movie.MovieInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieInfoRepository extends JpaRepository<MovieInfoEntity, Long> {
}
