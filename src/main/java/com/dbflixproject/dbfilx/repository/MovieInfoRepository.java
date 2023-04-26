package com.dbflixproject.dbfilx.repository;

import com.dbflixproject.dbfilx.entity.CompanyInfoEntity;
import com.dbflixproject.dbfilx.entity.movie.MovieInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieInfoRepository extends JpaRepository<MovieInfoEntity, Long> {
    @Query("select m from MovieInfoEntity m join fetch m.company where m.miSeq = :seq")
    MovieInfoEntity findSeqCompanyjoin(@Param("seq") Long seq);

    List<MovieInfoEntity> findByCompany(CompanyInfoEntity company);
}
