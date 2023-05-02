package com.dbflixproject.dbfilx.repository;

import com.dbflixproject.dbfilx.entity.AwardInfoEntity;
import com.dbflixproject.dbfilx.entity.enumfile.AwardCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AwardInfoRepository extends JpaRepository<AwardInfoEntity, Long> {
    Optional<AwardInfoEntity> findByAiSeqAndAiCate(Long seq, AwardCategory cate);

    List<AwardInfoEntity> findByAiCate(AwardCategory awardCategory);
}
