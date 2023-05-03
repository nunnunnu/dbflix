package com.dbflixproject.dbfilx.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewInsertDto {
    @NotNull(message = "회원번호 누락")
    private Long userSeq;

    @NotNull(message = "영화번호 누락")
    private Long movieSeq;

    @NotBlank(message = "리뷰내용 누락")
    private String comment;

    @Min(value = 1, message = "평점은 1~10사이로 입력해주세요")
    @Max(value = 10, message = "평점은 1~10사이로 입력해주세요")
    private int rating;
}
