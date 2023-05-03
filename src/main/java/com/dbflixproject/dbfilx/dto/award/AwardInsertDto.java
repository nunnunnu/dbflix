package com.dbflixproject.dbfilx.dto.award;

import com.dbflixproject.dbfilx.entity.enumfile.AwardCategory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AwardInsertDto {
    @NotBlank(message = "이름 누락")
    private String name;

//    @NotNull(message = "연도 누락(1900년도부터 시작)")
    @Min(value = 1900, message = "1900년도 이후부터 입력 가능합니다")
    private int year;

    @NotNull(message = "카테고리 누락")
    private AwardCategory category;

}
