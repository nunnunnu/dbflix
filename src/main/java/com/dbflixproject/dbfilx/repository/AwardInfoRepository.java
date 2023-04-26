package com.dbflixproject.dbfilx.repository;

import com.dbflixproject.dbfilx.entity.AwardInfoEntity;
import com.dbflixproject.dbfilx.entity.enumfile.AwardCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AwardInfoRepository extends JpaRepository<AwardInfoEntity, Long> {
    AwardInfoEntity findByAiSeqAndAiCate(Long seq, AwardCategory cate);
}
