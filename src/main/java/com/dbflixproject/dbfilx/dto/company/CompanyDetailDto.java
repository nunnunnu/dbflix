package com.dbflixproject.dbfilx.dto.company;

import com.dbflixproject.dbfilx.dto.movie.MovieInfoDto;
import com.dbflixproject.dbfilx.entity.CompanyInfoEntity;
import com.dbflixproject.dbfilx.entity.movie.MovieInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDetailDto {
    private String name;
    private String businessNum;
    private String address;
    private List<MovieInfoDto> createMovie = new ArrayList<>();

    public CompanyDetailDto(CompanyInfoEntity entity, List<MovieInfoEntity> movies){
        this.name = entity.getComName();
        this.businessNum = entity.getComBusinessNum();
        this.address = entity.getComAddress();
        createMovie = movies.stream().map(MovieInfoDto::new).collect(Collectors.toList());
    }
}
