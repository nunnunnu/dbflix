package com.dbflixproject.dbfilx.api;

import com.dbflixproject.dbfilx.dto.NewResponseDataDto;
import com.dbflixproject.dbfilx.dto.NewResponseDto;
import com.dbflixproject.dbfilx.dto.ResponseDto;
import com.dbflixproject.dbfilx.dto.review.FavoriteGenreDto;
import com.dbflixproject.dbfilx.dto.review.ReviewDetailDto;
import com.dbflixproject.dbfilx.dto.review.ReviewInsertDto;
import com.dbflixproject.dbfilx.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewApiController {
    private final ReviewService reviewService;
    @PostMapping()
    public ResponseEntity<NewResponseDto> insertReview(@RequestBody @Valid ReviewInsertDto data){
        NewResponseDto response = reviewService.insertReview(data);
        return new ResponseEntity<>(response, response.getCode());
    }

    @GetMapping("/{seq}")
    public ResponseEntity<NewResponseDataDto<Page<ReviewDetailDto>>> getMovieReview(
            @PathVariable Long seq,
            @PageableDefault(size=10, sort="riCreated", direction = Sort.Direction.DESC) Pageable page
    ){
        NewResponseDataDto<Page<ReviewDetailDto>> response = reviewService.getReview(seq, page);
        return new ResponseEntity<>(response, response.getCode());
    }
    @GetMapping("/genre/{seq}")
    public ResponseEntity<NewResponseDataDto<List<FavoriteGenreDto>>> myFavoriteGenre(@PathVariable Long seq){
        NewResponseDataDto<List<FavoriteGenreDto>> response = reviewService.myFavoriteGenre(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @DeleteMapping("/{seq}")
    public ResponseEntity<NewResponseDto> deleteReview(@PathVariable Long seq){
        NewResponseDto response = reviewService.deleteReview(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @GetMapping("/user/{seq}")
    public ResponseEntity<NewResponseDataDto<List<ReviewDetailDto>>> getMyReview(@PathVariable Long seq){
        NewResponseDataDto<List<ReviewDetailDto>> response = reviewService.myReview(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
}
