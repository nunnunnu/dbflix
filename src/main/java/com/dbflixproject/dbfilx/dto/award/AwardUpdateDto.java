package com.dbflixproject.dbfilx.dto.award;

import com.dbflixproject.dbfilx.entity.enumfile.AwardCategory;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AwardUpdateDto {
    private String name;

    @Min(value = 1900, message = "1900년도 이후부터 입력 가능합니다")
    private Integer year;

    private AwardCategory category;
}
