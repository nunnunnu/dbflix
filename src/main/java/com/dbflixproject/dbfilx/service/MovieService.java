package com.dbflixproject.dbfilx.service;

import com.dbflixproject.dbfilx.dto.ResponseDto;
import com.dbflixproject.dbfilx.dto.movie.MovieAddCreatorDto;
import com.dbflixproject.dbfilx.dto.movie.MovieDetailDto;
import com.dbflixproject.dbfilx.dto.movie.MovieInsertDto;
import com.dbflixproject.dbfilx.dto.movie.MovieUpdateDto;
import com.dbflixproject.dbfilx.entity.AwardInfoEntity;
import com.dbflixproject.dbfilx.entity.CompanyInfoEntity;
import com.dbflixproject.dbfilx.entity.creator.CreatorInfoEntity;
import com.dbflixproject.dbfilx.entity.creator.CreatorMovieConnectionEntity;
import com.dbflixproject.dbfilx.entity.enumfile.AwardCategory;
import com.dbflixproject.dbfilx.entity.movie.MovieAwardConnectionEntity;
import com.dbflixproject.dbfilx.entity.movie.MovieInfoEntity;
import com.dbflixproject.dbfilx.exception.NotFoundAwardException;
import com.dbflixproject.dbfilx.exception.NotFoundCompanyException;
import com.dbflixproject.dbfilx.exception.NotFoundCreatorException;
import com.dbflixproject.dbfilx.exception.NotFoundMovieException;
import com.dbflixproject.dbfilx.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieInfoRepository movieRepo;
    private final CompanyInfoRepository companyRepo;
    private final CreatorMovieConnectionRepository cMovieRepo;
    private final MovieAwardConnectionRepository mAwardRepo;
    private final CreatorInfoRepository creatorRepo;
    private final AwardInfoRepository awardRepo;
    private final ReviewInfoRepository reviewRepo;

    @Transactional
    public ResponseDto insertMovie(MovieInsertDto data){
        CompanyInfoEntity company = companyRepo.findById(data.getCompanySeq()).orElseThrow(()->new NotFoundCompanyException());
        MovieInfoEntity movie =
                new MovieInfoEntity(null, data.getAttendance(), data.getRegDt(), data.getName(), data.getPrice(), data.getCountry(), data.getGenre(), company);
        movieRepo.save(movie);
        return ResponseDto.builder().code(HttpStatus.OK).status(true).time(LocalDateTime.now()).message("등록 성공").build();
    }
    @Transactional(readOnly = true)
    public ResponseDto<MovieDetailDto> movieDetailShow(Long seq){
        MovieInfoEntity movie = movieRepo.findSeqCompanyjoin(seq);
        if(movie ==null){
            throw new NotFoundMovieException();
        }
        List<CreatorMovieConnectionEntity> creators = cMovieRepo.findByMovie(movie);
        List<MovieAwardConnectionEntity> awards = mAwardRepo.findByMovie(movie);
        Double rate = reviewRepo.movieRateAge(movie);
        MovieDetailDto result = new MovieDetailDto(movie, creators, awards, rate);
        return new ResponseDto<MovieDetailDto>("조회 성공", LocalDateTime.now(), true, result, HttpStatus.OK);
    }

    @Transactional
    public ResponseDto addCreator(MovieAddCreatorDto data){
        MovieInfoEntity movie = movieRepo.findById(data.getMovieSeq()).orElseThrow(()->new NotFoundMovieException());
        CreatorInfoEntity creator = creatorRepo.findById(data.getCreatorSeq()).orElseThrow(()->new NotFoundCreatorException());
        CreatorMovieConnectionEntity connect = new CreatorMovieConnectionEntity(null, creator, movie, data.getRole());
        if(cMovieRepo.existsByMovieAndCreator(movie, creator)){
            return ResponseDto.builder().status(false).code(HttpStatus.BAD_REQUEST).message("이미 등록된 영화인입니다.").time(LocalDateTime.now()).build();
        }
        cMovieRepo.save(connect);
        return ResponseDto.builder().status(true).code(HttpStatus.OK).message("영화인 추가 성공").time(LocalDateTime.now()).build();
    }
    @Transactional
    public ResponseDto addAward(Long movieSeq, Long awardSeq){
        MovieInfoEntity movie = movieRepo.findById(movieSeq).orElseThrow(()->new NotFoundMovieException());
        AwardInfoEntity award = awardRepo.findByAiSeqAndAiCate(awardSeq, AwardCategory.영화);
        if(award==null){
            throw new NotFoundAwardException();
        }
        if(mAwardRepo.existsByMovieAndAward(movie, award)){
            return ResponseDto.builder().time(LocalDateTime.now()).message("이미 등록된 상입니다.").code(HttpStatus.OK).status(true).build();
        }
        MovieAwardConnectionEntity entity = new MovieAwardConnectionEntity(null, movie, award);
        mAwardRepo.save(entity);
        return ResponseDto.builder().time(LocalDateTime.now()).message("등록완료").code(HttpStatus.OK).status(true).build();
    }
    @Transactional
    public ResponseDto updateMovie(Long seq, MovieUpdateDto data){
        MovieInfoEntity movie = movieRepo.findById(seq).orElseThrow(()->new NotFoundMovieException());
        movie.changeData(data.getName(), data.getAttendance(), data.getRegDt(), data.getPrice(), data.getCounty(), data.getGenre());
        movieRepo.save(movie);

        return ResponseDto.builder().time(LocalDateTime.now()).message("수정 완료").code(HttpStatus.OK).status(true).build();
    }


}
