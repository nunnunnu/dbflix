package com.dbflixproject.dbfilx.api;

import com.dbflixproject.dbfilx.dto.NewResponseDataDto;
import com.dbflixproject.dbfilx.dto.NewResponseDto;
import com.dbflixproject.dbfilx.dto.movie.MovieAddCreatorDto;
import com.dbflixproject.dbfilx.dto.movie.MovieInsertDto;
import com.dbflixproject.dbfilx.dto.movie.MovieRankingDto;
import com.dbflixproject.dbfilx.dto.movie.MovieUpdateDto;
import com.dbflixproject.dbfilx.entity.enumfile.MovieRole;
import com.dbflixproject.dbfilx.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movie")
@Tag(name="영화")
public class MovieApiController {
    private final MovieService movieService;

    @Operation(summary = "영화 추가")
    @PostMapping
    public ResponseEntity<NewResponseDto> insertMovie(@Valid @RequestBody MovieInsertDto data){
        NewResponseDto response = movieService.insertMovie(data);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "영화 상세정보 조회")
    @GetMapping("/{seq}")
    public ResponseEntity<NewResponseDto> movieDetailInfo(@PathVariable Long seq){
        NewResponseDto response = movieService.movieDetailShow(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "영화 출연진/감독 정보 추가")
    @PostMapping("/creator")
    public ResponseEntity<NewResponseDto> movieAddCreator(@Valid @RequestBody MovieAddCreatorDto data){
        NewResponseDto response = movieService.addCreator(data);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "영화 상정보 추가")
    @PostMapping("/award/{movieSeq}/{awardSeq}")
    public ResponseEntity<NewResponseDto> movieAddAward(@PathVariable Long movieSeq, @PathVariable Long awardSeq){
        NewResponseDto response = movieService.addAward(movieSeq, awardSeq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "영화 정보 수정")
    @PutMapping("/{seq}")
    public ResponseEntity<NewResponseDto> movieUpdateInfo(@PathVariable Long seq, @RequestBody MovieUpdateDto data){
        NewResponseDto response = movieService.updateMovie(seq, data);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "영화 순위(관객수/평점순)")
    @GetMapping("/ranking/{type}")
    public ResponseEntity<NewResponseDataDto<List<MovieRankingDto>>> movieRanking(@PathVariable String type){
        NewResponseDataDto<List<MovieRankingDto>> response = movieService.MovieRanking(type);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "영화 정보 삭제")
    @DeleteMapping("/{seq}")
    public ResponseEntity<NewResponseDto> movieDelete(@PathVariable Long seq){
        NewResponseDto response = movieService.movieDelete(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "영화 수상내역 삭제")
    @DeleteMapping("/award/{seq}")
    public ResponseEntity<NewResponseDto> deleteMovieAward(@PathVariable Long seq){
        NewResponseDto response = movieService.deleteMovieAward(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "영화 출연진/감독 삭제")
    @DeleteMapping("/creator/{seq}")
    public ResponseEntity<NewResponseDto> deleteMovieCreator(@PathVariable Long seq){
        NewResponseDto response = movieService.deleteMovieCreator(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "영화 출연 역할 수정")
    @PatchMapping("/creator/{seq}/{type}")
    public ResponseEntity<NewResponseDto> updateMovieRole(@PathVariable Long seq, @PathVariable MovieRole type){
        NewResponseDto response = movieService.updateMovieRole(seq, type);
        return new ResponseEntity<>(response, response.getCode());
    }
}