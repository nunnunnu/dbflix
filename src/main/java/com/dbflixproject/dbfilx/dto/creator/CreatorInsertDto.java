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
    @NotBlank
    private String name;
    @NotBlank
    private String country;
    @NotBlank
    private Integer age;
    @NotBlank
    private CreatorType type;
    @NotBlank
    private Gender gen;
}
