package com.dbflixproject.dbfilx.dto.user;

import com.dbflixproject.dbfilx.entity.enumfile.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDto {
    @NotBlank(message = "현재비밀번호를 입력해주세요.")
    private String originPwd;
    private String changePwd;
    @Email(message="이메일 형식이 아닙니다.")
    private String email;
    private String name;
    private Gender gen;

}
