package com.dbflixproject.dbfilx.repository;

import com.dbflixproject.dbfilx.entity.AwardInfoEntity;
import com.dbflixproject.dbfilx.entity.movie.MovieAwardConnectionEntity;
import com.dbflixproject.dbfilx.entity.movie.MovieInfoEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieAwardConnectionRepository extends JpaRepository<MovieAwardConnectionEntity, Long> {
    @EntityGraph(attributePaths ={"award"})
    List<MovieAwardConnectionEntity> findByMovie(MovieInfoEntity movie);

    Boolean existsByMovieAndAward(MovieInfoEntity movie, AwardInfoEntity award);
}
