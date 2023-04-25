package com.dbflixproject.dbfilx.dto.user;

import com.dbflixproject.dbfilx.entity.enumfile.Gender;
import com.dbflixproject.dbfilx.entity.user.UserInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailDto {
    private String id;
    private String name;
    private String email;
    private String file;
    private LocalDate birth;
    private Gender gen;

    public UserDetailDto(UserInfoEntity entity){
        this.id = entity.getUiId();
        this.name = entity.getUiName();
        this.email = entity.getUiEmail();
        this.file = entity.getUiFile();
        this.birth = entity.getUiBirth();
        this.gen = entity.getUiGen();
    }

}
