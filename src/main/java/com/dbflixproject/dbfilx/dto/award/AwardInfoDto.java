package com.dbflixproject.dbfilx.dto.award;

import com.dbflixproject.dbfilx.entity.AwardInfoEntity;
import com.dbflixproject.dbfilx.entity.enumfile.AwardCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AwardInfoDto {
    private String name;
    private Integer year;
    private AwardCategory cate;

    public AwardInfoDto(AwardInfoEntity entity){
        this.name = entity.getAiName();
        this.year = entity.getAiYear();
        this.cate = entity.getAiCate();
    }
}
