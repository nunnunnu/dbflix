package com.dbflixproject.dbfilx.dto.company;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUpdateDto {
    private String name;
    private String address;
    @Pattern(regexp = "([0-9]{3})-?([0-9]{2})-?([0-9]{5})", message = "사업자번호 형식이 일치하지않습니다")
    private String businessNum;

}
