package com.dbflixproject.dbfilx.api;

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
    public ResponseEntity<ResponseDto<?>> insertReview(@RequestBody @Valid ReviewInsertDto data){
        ResponseDto<?> response = reviewService.insertReview(data);
        return new ResponseEntity<>(response, response.getCode());
    }

    @GetMapping("/{seq}")
    public ResponseEntity<ResponseDto<Page<ReviewDetailDto>>> getMovieReview(
            @PathVariable Long seq,
            @PageableDefault(size=10, sort="riCreated", direction = Sort.Direction.DESC) Pageable page
    ){
        ResponseDto<Page<ReviewDetailDto>> response = reviewService.getReview(seq, page);
        return new ResponseEntity<>(response, response.getCode());
    }
    @GetMapping("/genre/{seq}")
    public ResponseEntity<ResponseDto<List<FavoriteGenreDto>>> myFavoriteGenre(@PathVariable Long seq){
        ResponseDto<List<FavoriteGenreDto>> response = reviewService.myFavoriteGenre(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @DeleteMapping("/{seq}")
    public ResponseEntity<ResponseDto<?>> deleteReview(@PathVariable Long seq){
        ResponseDto<?> response = reviewService.deleteReview(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @GetMapping("/user/{seq}")
    public ResponseEntity<ResponseDto<List<ReviewDetailDto>>> getMyReview(@PathVariable Long seq){
        ResponseDto<List<ReviewDetailDto>> response = reviewService.myReview(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
}
