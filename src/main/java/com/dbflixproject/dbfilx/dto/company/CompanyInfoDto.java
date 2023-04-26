package com.dbflixproject.dbfilx.dto.company;

import com.dbflixproject.dbfilx.entity.CompanyInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyInfoDto {
    private Long seq;
    private String name;

    public CompanyInfoDto(CompanyInfoEntity company) {
        this.seq = company.getComSeq();
        this.name = company.getComName();
    }
}
