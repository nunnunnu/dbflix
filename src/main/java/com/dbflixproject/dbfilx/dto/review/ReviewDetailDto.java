package com.dbflixproject.dbfilx.dto.review;

import com.dbflixproject.dbfilx.dto.movie.MovieInfoDto;
import com.dbflixproject.dbfilx.entity.ReviewInfoEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDetailDto {
    private String userName;
    private String comment;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime regDt;
    private Integer rate;
    private MovieInfoDto movie;

    public ReviewDetailDto(ReviewInfoEntity review){
        this.userName = review.getUser().getUiName();
        this.comment = review.getRiComment();
        this.regDt = review.getRiCreated();
        this.rate = review.getRiRate();
        this.movie = new MovieInfoDto(review.getMovie());
    }
}
