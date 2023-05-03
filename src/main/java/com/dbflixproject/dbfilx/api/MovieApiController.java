package com.dbflixproject.dbfilx.api;

import com.dbflixproject.dbfilx.dto.NewResponseDataDto;
import com.dbflixproject.dbfilx.dto.NewResponseDto;
import com.dbflixproject.dbfilx.dto.movie.MovieAddCreatorDto;
import com.dbflixproject.dbfilx.dto.movie.MovieInsertDto;
import com.dbflixproject.dbfilx.dto.movie.MovieRankingDto;
import com.dbflixproject.dbfilx.dto.movie.MovieUpdateDto;
import com.dbflixproject.dbfilx.entity.enumfile.MovieRole;
import com.dbflixproject.dbfilx.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movie")
public class MovieApiController {
    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<NewResponseDto> insertMovie(@Valid @RequestBody MovieInsertDto data){
        NewResponseDto response = movieService.insertMovie(data);
        return new ResponseEntity<>(response, response.getCode());
    }
    @GetMapping("/{seq}")
    public ResponseEntity<NewResponseDto> movieDetailInfo(@PathVariable Long seq){
        NewResponseDto response = movieService.movieDetailShow(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @PostMapping("/creator")
    public ResponseEntity<NewResponseDto> movieAddCreator(@Valid @RequestBody MovieAddCreatorDto data){
        NewResponseDto response = movieService.addCreator(data);
        return new ResponseEntity<>(response, response.getCode());
    }
    @PostMapping("/award/{movieSeq}/{awardSeq}")
    public ResponseEntity<NewResponseDto> movieAddAward(@PathVariable Long movieSeq, @PathVariable Long awardSeq){
        NewResponseDto response = movieService.addAward(movieSeq, awardSeq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @PutMapping("/{seq}")
    public ResponseEntity<NewResponseDto> movieUpdateInfo(@PathVariable Long seq, @RequestBody MovieUpdateDto data){
        NewResponseDto response = movieService.updateMovie(seq, data);
        return new ResponseEntity<>(response, response.getCode());
    }
    @GetMapping("/ranking/{type}")
    public ResponseEntity<NewResponseDataDto<List<MovieRankingDto>>> movieRanking(@PathVariable String type){
        NewResponseDataDto<List<MovieRankingDto>> response = movieService.MovieRanking(type);
        return new ResponseEntity<>(response, response.getCode());
    }
    @DeleteMapping("/{seq}")
    public ResponseEntity<NewResponseDto> movieDelete(@PathVariable Long seq){
        NewResponseDto response = movieService.movieDelete(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @DeleteMapping("/award/{seq}")
    public ResponseEntity<NewResponseDto> deleteMovieAward(@PathVariable Long seq){
        NewResponseDto response = movieService.deleteMovieAward(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @DeleteMapping("/creator/{seq}")
    public ResponseEntity<NewResponseDto> deleteMovieCreator(@PathVariable Long seq){
        NewResponseDto response = movieService.deleteMovieCreator(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @PatchMapping("/creator/{seq}/{type}")
    public ResponseEntity<NewResponseDto> updateMovieRole(@PathVariable Long seq, @PathVariable MovieRole type){
        NewResponseDto response = movieService.updateMovieRole(seq, type);
        return new ResponseEntity<>(response, response.getCode());
    }
}
