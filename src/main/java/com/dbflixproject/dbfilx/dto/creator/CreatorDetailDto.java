package com.dbflixproject.dbfilx.dto.creator;

import com.dbflixproject.dbfilx.dto.AwardInfoDto;
import com.dbflixproject.dbfilx.entity.creator.CreatorAwardConnectionEntity;
import com.dbflixproject.dbfilx.entity.creator.CreatorInfoEntity;
import com.dbflixproject.dbfilx.entity.creator.CreatorMovieConnectionEntity;
import com.dbflixproject.dbfilx.entity.enumfile.CreatorType;
import com.dbflixproject.dbfilx.entity.enumfile.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<MovieRoleDto> movies = new ArrayList<>();

    public CreatorDetailDto(CreatorInfoEntity creator, List<CreatorAwardConnectionEntity> award, List<CreatorMovieConnectionEntity> movie) {
        this.name = creator.getCiName();
        this.gen = creator.getCiGen();
        this.age = creator.getCiAge();
        this.country = creator.getCiCountry();
        this.type = creator.getCiRole();
        awards = award.stream().map(a -> new AwardInfoDto(a.getAward())).collect(Collectors.toList());
        movies = movie.stream().map(m -> new MovieRoleDto(m)).collect(Collectors.toList());

    }
}
