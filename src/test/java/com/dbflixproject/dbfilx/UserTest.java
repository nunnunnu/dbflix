package com.dbflixproject.dbfilx;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import com.dbflixproject.dbfilx.entity.enumfile.Gender;
import com.dbflixproject.dbfilx.entity.user.UserInfoEntity;
import com.dbflixproject.dbfilx.repository.UserInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
@Transactional
public class UserTest {
    @Autowired
    UserInfoRepository userRepo;

    private UserInfoEntity user;
    @BeforeEach
    public void beforeEach(){
        user = new UserInfoEntity();
        user.joinData("testid001", "testpwd", "testname", "testemail@email.com", LocalDate.now(), Gender.선택안함);
        userRepo.save(user);
    }

    @Test
    public void 회원가입(){
        UserInfoEntity newUser = new UserInfoEntity();
        String id = "testid002";
        newUser.joinData(id, "testpwd2", "testname", "testemail@email.com", LocalDate.now(), Gender.선택안함);
        userRepo.save(newUser);

        UserInfoEntity findUser = userRepo.findById(newUser.getUiSeq()).orElseThrow();

        assertThat(findUser).isNotNull();
        assertThat(findUser.getUiId()).isEqualTo(id);
    }
    @Test
    public void 회원가입실패_아이디중복(){
        if(!userRepo.existsByUiId(user.getUiId())){
            fail();
            UserInfoEntity newUser = new UserInfoEntity();
            String id = "testid003";
            newUser.joinData(id, "testpwd2", "testname", "testemail@email.com", LocalDate.now(), Gender.선택안함);
            userRepo.save(newUser);
        }
    }
    @Test
    public void 회원탈퇴(){
        user.dropUser();
        userRepo.save(user);

        assertThat(user.getUiStatus()).isFalse();
    }
    @Test
    public void 회원탈퇴_실패(){
        user.dropUser();
        userRepo.save(user);
        if(user.getUiStatus()){
            fail();
            user.dropUser();
            userRepo.save(user);
        }
    }
}
