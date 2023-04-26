package com.dbflixproject.dbfilx.repository;

import com.dbflixproject.dbfilx.entity.ReviewInfoEntity;
import com.dbflixproject.dbfilx.entity.movie.MovieInfoEntity;
import com.dbflixproject.dbfilx.entity.user.UserInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewInfoRepository extends JpaRepository<ReviewInfoEntity, Long> {
    Boolean existsByUserAndMovie(UserInfoEntity user, MovieInfoEntity review);

    @Query("select avg(r.riRate) from ReviewInfoEntity r where r.movie=:movie")
    Double movieRateAge(@Param("movie") MovieInfoEntity movie);

    Page<ReviewInfoEntity> findByMovie(MovieInfoEntity movie, Pageable page);

}
