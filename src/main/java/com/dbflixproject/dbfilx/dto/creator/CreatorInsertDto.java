package com.dbflixproject.dbfilx.dto.creator;

import com.dbflixproject.dbfilx.entity.enumfile.CreatorType;
import com.dbflixproject.dbfilx.entity.enumfile.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatorInsertDto {
    @NotBlank(message = "이름을 입력해주세요")
    private String name;
    @NotBlank(message = "국적을 입력해주세요")
    private String country;
    @NotBlank(message = "나이를 입력해주세요")
    private Integer age;
    @NotBlank(message = "영화인 타입을 입력해주세요(배우/감독)")
    private CreatorType type;
    @NotBlank(message = "성별을 입력해주세요(남/여/선택안함)")
    private Gender gen;
}
