package com.dbflixproject.dbfilx.repository;

import com.dbflixproject.dbfilx.entity.creator.CreatorInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatorInfoRepository extends JpaRepository<CreatorInfoEntity, Long> {
}