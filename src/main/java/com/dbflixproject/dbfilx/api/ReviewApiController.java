package com.dbflixproject.dbfilx.api;

import com.dbflixproject.dbfilx.dto.NewResponseDataDto;
import com.dbflixproject.dbfilx.dto.NewResponseDto;
import com.dbflixproject.dbfilx.dto.review.FavoriteGenreDto;
import com.dbflixproject.dbfilx.dto.review.ReviewDetailDto;
import com.dbflixproject.dbfilx.dto.review.ReviewInsertDto;
import com.dbflixproject.dbfilx.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
@Tag(name="리뷰")
public class ReviewApiController {
    private final ReviewService reviewService;
    @Operation(summary = "리뷰 등록")
    @PostMapping
    public ResponseEntity<NewResponseDto> insertReview(@RequestBody @Valid ReviewInsertDto data){
        NewResponseDto response = reviewService.insertReview(data);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "영화 리뷰조회",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY
                            , description = "페이지번호(0부터 시작), 입력안하면 0페이지 조회"
                            , name = "page"
                            , content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "입력안해도됨. 기본 한 페이지 당 10개 씩"
                            , name = "size"),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "입력안해도됨. 기본 최신순정렬"
                            , name = "sort")
            })
    @GetMapping("/{seq}")
    public ResponseEntity<NewResponseDataDto<Page<ReviewDetailDto>>> getMovieReview(
            @PathVariable Long seq,
            @Parameter(hidden=true) @PageableDefault(size=10, sort="riCreated", direction = Sort.Direction.DESC) Pageable page
    ){
        NewResponseDataDto<Page<ReviewDetailDto>> response = reviewService.getReview(seq, page);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "회원 선호 장르(작성리뷰 장르별 평균)")
    @GetMapping("/genre/{seq}")
    public ResponseEntity<NewResponseDataDto<List<FavoriteGenreDto>>> myFavoriteGenre(@PathVariable Long seq){
        NewResponseDataDto<List<FavoriteGenreDto>> response = reviewService.myFavoriteGenre(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "리뷰 삭제")
    @DeleteMapping("/{seq}")
    public ResponseEntity<NewResponseDto> deleteReview(@PathVariable Long seq){
        NewResponseDto response = reviewService.deleteReview(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "작성한 리뷰 모아보기",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY
                            , description = "페이지번호(0부터 시작), 입력안하면 0페이지 조회"
                            , name = "page"
                            , content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "입력안해도됨. 기본 한 페이지 당 10개 씩"
                            , name = "size"),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "입력안해도됨. 기본 최신순정렬"
                            , name = "sort")
            })
    @GetMapping("/user/{seq}")
    public ResponseEntity<NewResponseDataDto<Slice<ReviewDetailDto>>> getMyReview(@PathVariable Long seq,
            @Parameter(hidden=true) @PageableDefault(size=5, sort="riCreated", direction = Sort.Direction.DESC) Pageable page
    ){
        NewResponseDataDto<Slice<ReviewDetailDto>> response = reviewService.myReview(seq, page);
        return new ResponseEntity<>(response, response.getCode());
    }
}