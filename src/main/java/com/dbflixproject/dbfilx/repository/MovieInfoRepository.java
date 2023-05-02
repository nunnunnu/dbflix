package com.dbflixproject.dbfilx.repository;

import com.dbflixproject.dbfilx.dto.movie.MovieRankingDto;
import com.dbflixproject.dbfilx.entity.CompanyInfoEntity;
import com.dbflixproject.dbfilx.entity.movie.MovieInfoEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieInfoRepository extends JpaRepository<MovieInfoEntity, Long> {
    @Query("select m from MovieInfoEntity m join fetch m.company where m.miSeq = :seq")
    Optional<MovieInfoEntity> findSeqCompanyjoin(@Param("seq") Long seq);

    List<MovieInfoEntity> findByCompany(CompanyInfoEntity company);

    @Query("select m.miSeq as seq, m.miTitle as name, m.miAttendance as attendance, " +
            "(select avg(r.riRate) as rate from ReviewInfoEntity r where r.movie.miSeq = m.miSeq) as rate " +
            " from MovieInfoEntity m")
    List<MovieRankingDto> rateRanking(Sort sort);
}
