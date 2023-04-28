package com.dbflixproject.dbfilx.repository;

import com.dbflixproject.dbfilx.entity.creator.CreatorInfoEntity;
import com.dbflixproject.dbfilx.entity.enumfile.CreatorType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreatorInfoRepository extends JpaRepository<CreatorInfoEntity, Long> {
    List<CreatorInfoEntity> findByCiRole(CreatorType creatorType);
}