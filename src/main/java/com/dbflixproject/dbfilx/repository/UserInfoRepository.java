package com.dbflixproject.dbfilx.repository;

import com.dbflixproject.dbfilx.entity.user.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {
    Boolean existsByUiId(String id);
    Boolean existsByUiEmail(String email);
    UserInfoEntity findByUiSeqAndUiStatus(Long seq, Boolean status);
    UserInfoEntity findByUiFile(String fileName);
}
