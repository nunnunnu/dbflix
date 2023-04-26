package com.dbflixproject.dbfilx.repository;

import com.dbflixproject.dbfilx.entity.AwardInfoEntity;
import com.dbflixproject.dbfilx.entity.creator.CreatorAwardConnectionEntity;
import com.dbflixproject.dbfilx.entity.creator.CreatorInfoEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreatorAwardConnectionRepository extends JpaRepository<CreatorAwardConnectionEntity, Long> {
    @EntityGraph(attributePaths = {"creator"})
    List<CreatorAwardConnectionEntity> findByCreator(CreatorInfoEntity entity);
    Boolean existsByCreatorAndAward(CreatorInfoEntity creator, AwardInfoEntity award);
}
