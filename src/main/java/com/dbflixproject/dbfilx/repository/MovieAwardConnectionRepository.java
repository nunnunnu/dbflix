package com.dbflixproject.dbfilx.repository;

import com.dbflixproject.dbfilx.entity.movie.MovieAwardConnectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieAwardConnectionRepository extends JpaRepository<MovieAwardConnectionEntity, Long> {
}
