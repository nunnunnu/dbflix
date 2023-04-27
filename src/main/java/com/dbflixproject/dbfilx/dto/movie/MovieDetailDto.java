package com.dbflixproject.dbfilx.dto.movie;

import com.dbflixproject.dbfilx.dto.award.AwardInfoDto;
import com.dbflixproject.dbfilx.dto.company.CompanyInfoDto;
import com.dbflixproject.dbfilx.dto.creator.MovieRoleDto;
import com.dbflixproject.dbfilx.entity.creator.CreatorMovieConnectionEntity;
import com.dbflixproject.dbfilx.entity.enumfile.MovieGenre;
import com.dbflixproject.dbfilx.entity.movie.MovieAwardConnectionEntity;
import com.dbflixproject.dbfilx.entity.movie.MovieInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDetailDto {
    private String name;
    private Integer attendance;
    private LocalDate regDt;
    private Integer price;
    private String country;
    private MovieGenre genre;
    private CompanyInfoDto company;
    private Double rate;
    private List<MovieRoleDto> role = new ArrayList<>();
    private List<AwardInfoDto> award = new ArrayList<>();

    public MovieDetailDto(MovieInfoEntity entity, List<CreatorMovieConnectionEntity> creator, List<MovieAwardConnectionEntity> award, Double rate){
        this.name = entity.getMiTitle();
        this.attendance = entity.getMiAttendance();
        this.regDt = entity.getMiRegDt();
        this.price = entity.getMiPrice();
        this.country = entity.getMiContry();
        this.genre = entity.getMiGenre();
        this.company = new CompanyInfoDto(entity.getCompany());
        this.rate = rate;

        this.role = creator.stream().map((c)->new MovieRoleDto(c)).collect(Collectors.toList());
        this.award = award.stream().map((a)->new AwardInfoDto(a.getAward())).collect(Collectors.toList());
    }
}
