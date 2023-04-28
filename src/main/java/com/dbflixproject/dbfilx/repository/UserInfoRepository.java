package com.dbflixproject.dbfilx.repository;

import com.dbflixproject.dbfilx.entity.user.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {
    Boolean existsByUiId(String id);
    Boolean existsByUiEmail(String email);
    UserInfoEntity findByUiSeqAndUiStatus(Long seq, Boolean status);
    UserInfoEntity findByUiFile(String fileName);

//    @Query("select u from UserInfoEntity u left join fetch u.review where u.uiSeq=:seq and u.uiStatus=:status")
//    UserInfoEntity findSeqReviewJoin(@Param("seq") Long seq, @Param("status") boolean status);
}
