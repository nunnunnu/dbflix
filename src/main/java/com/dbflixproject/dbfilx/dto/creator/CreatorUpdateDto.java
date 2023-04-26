package com.dbflixproject.dbfilx.dto.creator;

import com.dbflixproject.dbfilx.entity.enumfile.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatorUpdateDto {
    private String name;
    private String country;
    private Integer age;
    private Gender gen;
}
