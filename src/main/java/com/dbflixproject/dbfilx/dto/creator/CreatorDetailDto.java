package com.dbflixproject.dbfilx.dto.creator;

import com.dbflixproject.dbfilx.dto.AwardInfoDto;
import com.dbflixproject.dbfilx.dto.movie.MovieInfoDto;
import com.dbflixproject.dbfilx.entity.enumfile.CreatorType;
import com.dbflixproject.dbfilx.entity.enumfile.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatorDetailDto {
    private String name;
    private Gender gen;
    private Integer age;
    private String country;
    private CreatorType type;
    private List<AwardInfoDto> awards = new ArrayList<>();
    private List<MovieInfoDto> movies = new ArrayList<>();
}
