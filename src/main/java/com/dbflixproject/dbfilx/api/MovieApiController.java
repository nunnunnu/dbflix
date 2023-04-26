package com.dbflixproject.dbfilx.api;

import com.dbflixproject.dbfilx.dto.ResponseDto;
import com.dbflixproject.dbfilx.dto.movie.MovieAddCreatorDto;
import com.dbflixproject.dbfilx.dto.movie.MovieInsertDto;
import com.dbflixproject.dbfilx.dto.movie.MovieRankingDto;
import com.dbflixproject.dbfilx.dto.movie.MovieUpdateDto;
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

    @PostMapping()
    public ResponseEntity<ResponseDto<?>> insertMovie(@Valid @RequestBody MovieInsertDto data){
        ResponseDto<?> response = movieService.insertMovie(data);
        return new ResponseEntity<>(response, response.getCode());
    }
    @GetMapping("/{seq}")
    public ResponseEntity<ResponseDto<?>> movieDetailInfo(@PathVariable Long seq){
        ResponseDto<?> response = movieService.movieDetailShow(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @PostMapping("/creator")
    public ResponseEntity<ResponseDto<?>> movieAddCreator(@Valid @RequestBody MovieAddCreatorDto data){
        ResponseDto<?> response = movieService.addCreator(data);
        return new ResponseEntity<>(response, response.getCode());
    }
    @PostMapping("/award/{movieSeq}/{awardSeq}")
    public ResponseEntity<ResponseDto<?>> movieAddAward(@PathVariable Long movieSeq, @PathVariable Long awardSeq){
        ResponseDto<?> response = movieService.addAward(movieSeq, awardSeq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @PutMapping("/{seq}")
    public ResponseEntity<ResponseDto<?>> movieUpdateInfo(@PathVariable Long seq, @RequestBody MovieUpdateDto data){
        ResponseDto<?> response = movieService.updateMovie(seq, data);
        return new ResponseEntity<>(response, response.getCode());
    }
    @GetMapping("/ranking/{type}")
    public ResponseEntity<ResponseDto<List<MovieRankingDto>>> movieRanking(@PathVariable String type){
        ResponseDto<List<MovieRankingDto>> response = movieService.MovieRanking(type);
        return new ResponseEntity<>(response, response.getCode());
    }

}
