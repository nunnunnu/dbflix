package com.dbflixproject.dbfilx.dto.movie;

import com.dbflixproject.dbfilx.entity.enumfile.MovieGenre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieInsertDto {
    @NotBlank(message = "이름을 입력해주세요")
    private String name;
    @NotNull(message = "관람객 수를 입력해주세요")
    private Integer attendance;
    @NotNull(message = "개봉일 입력해주세요")
    private LocalDate regDt;
    @NotNull(message = "제작비용을 입력해주세요")
    private Integer price;
    @NotBlank(message = "개봉국가를 입력해주세요")
    private String country;
    @NotNull(message = "장르를 입력해주세요")
    private MovieGenre genre;
    @NotNull(message = "제작사 번호를 입력해주세요")
    private Long companySeq;
}
