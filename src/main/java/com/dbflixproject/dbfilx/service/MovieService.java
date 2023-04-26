package com.dbflixproject.dbfilx.service;

import com.dbflixproject.dbfilx.dto.ResponseDto;
import com.dbflixproject.dbfilx.dto.movie.MovieInsertDto;
import com.dbflixproject.dbfilx.entity.CompanyInfoEntity;
import com.dbflixproject.dbfilx.entity.movie.MovieInfoEntity;
import com.dbflixproject.dbfilx.exception.NotFoundCompanyException;
import com.dbflixproject.dbfilx.repository.CompanyInfoRepository;
import com.dbflixproject.dbfilx.repository.MovieInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieInfoRepository movieRepo;
    private final CompanyInfoRepository companyRepo;

    @Transactional
    public ResponseDto insertMovie(MovieInsertDto data){
        CompanyInfoEntity company = companyRepo.findById(data.getCompanySeq()).orElseThrow(()->new NotFoundCompanyException());
        MovieInfoEntity movie =
                new MovieInfoEntity(null, data.getAttendance(), data.getRegDt(), data.getName(), data.getPrice(), data.getCountry(), data.getGenre(), company);
        movieRepo.save(movie);
        return ResponseDto.builder().code(HttpStatus.OK).status(true).time(LocalDateTime.now()).message("등록 성공").build();
    }
}
