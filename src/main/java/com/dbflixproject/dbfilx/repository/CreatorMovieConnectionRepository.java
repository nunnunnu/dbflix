package com.dbflixproject.dbfilx.repository;

import com.dbflixproject.dbfilx.entity.creator.CreatorInfoEntity;
import com.dbflixproject.dbfilx.entity.creator.CreatorMovieConnectionEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreatorMovieConnectionRepository extends JpaRepository<CreatorMovieConnectionEntity, Long> {
    @EntityGraph(attributePaths = {"movie"})
    List<CreatorMovieConnectionEntity> findByCreator(CreatorInfoEntity creator);
}
