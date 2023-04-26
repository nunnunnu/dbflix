package com.dbflixproject.dbfilx.api;

import com.dbflixproject.dbfilx.dto.ResponseDto;
import com.dbflixproject.dbfilx.dto.movie.MovieInsertDto;
import com.dbflixproject.dbfilx.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movie")
public class MovieApiController {
    private final MovieService movieService;

    @PostMapping()
    public ResponseEntity insertMovie(@Valid @RequestBody MovieInsertDto data){
        ResponseDto response = movieService.insertMovie(data);
        return new ResponseEntity(response, response.getCode());
    }
}
