package com.dbflixproject.dbfilx.repository;

import com.dbflixproject.dbfilx.entity.AwardInfoEntity;
import com.dbflixproject.dbfilx.entity.enumfile.AwardCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AwardInfoRepository extends JpaRepository<AwardInfoEntity, Long> {
    AwardInfoEntity findByAiSeqAndAiCate(Long seq, AwardCategory cate);

    List<AwardInfoEntity> findByAiCate(AwardCategory awardCategory);
}
