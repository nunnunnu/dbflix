package com.dbflixproject.dbfilx.repository;

import com.dbflixproject.dbfilx.dto.review.FavoriteGenreDto;
import com.dbflixproject.dbfilx.entity.ReviewInfoEntity;
import com.dbflixproject.dbfilx.entity.movie.MovieInfoEntity;
import com.dbflixproject.dbfilx.entity.user.UserInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewInfoRepository extends JpaRepository<ReviewInfoEntity, Long> {
    Boolean existsByUserAndMovie(UserInfoEntity user, MovieInfoEntity review);

    @Query("select avg(r.riRate) from ReviewInfoEntity r where r.movie=:movie")
    Double movieRateAge(@Param("movie") MovieInfoEntity movie);

    Page<ReviewInfoEntity> findByMovie(MovieInfoEntity movie, Pageable page);

    @Query("select m.miGenre as genre, avg(r.riRate) as rate from ReviewInfoEntity r join MovieInfoEntity m on m.miSeq=r.movie.miSeq " +
            "where r.user.uiSeq=:seq" +
            " group by m.miGenre order by rate desc")
    List<FavoriteGenreDto> favoriteGenre(@Param("seq") Long seq);
    @EntityGraph(attributePaths = {"movie"})
    List<ReviewInfoEntity> findByUser(UserInfoEntity user);
}
