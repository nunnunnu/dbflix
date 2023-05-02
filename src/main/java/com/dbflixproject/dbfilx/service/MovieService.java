package com.dbflixproject.dbfilx.service;

import com.dbflixproject.dbfilx.dto.NewResponseDataDto;
import com.dbflixproject.dbfilx.dto.NewResponseDto;
import com.dbflixproject.dbfilx.dto.ResponseDto;
import com.dbflixproject.dbfilx.dto.movie.*;
import com.dbflixproject.dbfilx.entity.AwardInfoEntity;
import com.dbflixproject.dbfilx.entity.CompanyInfoEntity;
import com.dbflixproject.dbfilx.entity.creator.CreatorInfoEntity;
import com.dbflixproject.dbfilx.entity.creator.CreatorMovieConnectionEntity;
import com.dbflixproject.dbfilx.entity.enumfile.AwardCategory;
import com.dbflixproject.dbfilx.entity.enumfile.MovieRole;
import com.dbflixproject.dbfilx.entity.movie.MovieAwardConnectionEntity;
import com.dbflixproject.dbfilx.entity.movie.MovieInfoEntity;
import com.dbflixproject.dbfilx.exception.*;
import com.dbflixproject.dbfilx.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public NewResponseDto insertMovie(MovieInsertDto data){
        CompanyInfoEntity company = companyRepo.findById(data.getCompanySeq()).orElseThrow(()->new NotFoundEntityException("제작사"));
        MovieInfoEntity movie =
                new MovieInfoEntity(null, data.getAttendance(), data.getRegDt(), data.getName(), data.getPrice(), data.getCountry(), data.getGenre(), company);
        movieRepo.save(movie);
        return new NewResponseDto("등록 성공", HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    public NewResponseDataDto<MovieDetailDto> movieDetailShow(Long seq){
        MovieInfoEntity movie = movieRepo.findSeqCompanyjoin(seq).orElseThrow(()->new NotFoundEntityException("영화"));

        List<CreatorMovieConnectionEntity> creators = cMovieRepo.findByMovie(movie);
        List<MovieAwardConnectionEntity> awards = mAwardRepo.findByMovie(movie);
        Double rate = reviewRepo.movieRateAge(movie);
        MovieDetailDto result = new MovieDetailDto(movie, creators, awards, rate);

        return new NewResponseDataDto<>("조회 성공", HttpStatus.OK, result);
    }

    @Transactional
    public NewResponseDto addCreator(MovieAddCreatorDto data){
        MovieInfoEntity movie = movieRepo.findById(data.getMovieSeq()).orElseThrow(()->new NotFoundEntityException("영화"));
        CreatorInfoEntity creator = creatorRepo.findById(data.getCreatorSeq()).orElseThrow(()->new NotFoundEntityException("제작사"));
        CreatorMovieConnectionEntity connect = new CreatorMovieConnectionEntity(null, creator, movie, data.getRole());
        if(cMovieRepo.existsByMovieAndCreator(movie, creator)){
            return new NewResponseDto("이미 등록된 영화", HttpStatus.BAD_REQUEST);
        }
        cMovieRepo.save(connect);
        return new NewResponseDto("영화인 추가 성공", HttpStatus.OK);
    }
    @Transactional
    public NewResponseDto addAward(Long movieSeq, Long awardSeq){
        MovieInfoEntity movie = movieRepo.findById(movieSeq).orElseThrow(()->new NotFoundEntityException("영화"));
        AwardInfoEntity award = awardRepo.findByAiSeqAndAiCate(awardSeq, AwardCategory.영화).orElseThrow(()-> new NotFoundEntityException("상"));

        if(mAwardRepo.existsByMovieAndAward(movie, award)){
            return new NewResponseDto("이미 추가된 상입니다.", HttpStatus.BAD_REQUEST);
        }
        MovieAwardConnectionEntity entity = new MovieAwardConnectionEntity(null, movie, award);
        mAwardRepo.save(entity);
        return new NewResponseDto("등록 성공", HttpStatus.OK);
    }
    @Transactional
    public NewResponseDto updateMovie(Long seq, MovieUpdateDto data){
        MovieInfoEntity movie = movieRepo.findById(seq).orElseThrow(()->new NotFoundEntityException("영화"));
        movie.changeData(data.getName(), data.getAttendance(), data.getRegDt(), data.getPrice(), data.getCounty(), data.getGenre());
        movieRepo.save(movie);

        return new NewResponseDto("수정 성공", HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    public NewResponseDataDto<List<MovieRankingDto>> MovieRanking(String type){
        if(!type.equals("attendance") && !type.equals("rate")){
            return new NewResponseDataDto<>("타입 오류(attendance/rate)", HttpStatus.BAD_REQUEST, null);
        }
        List<MovieRankingDto> movies = movieRepo.rateRanking(Sort.by(type).descending());

        return new NewResponseDataDto<>("조회 성공", HttpStatus.OK, movies);
    }

    public NewResponseDto movieDelete(Long seq) {
        MovieInfoEntity movie = movieRepo.findById(seq).orElseThrow(()-> new NotFoundEntityException("영화"));
        movieRepo.delete(movie);

        return new NewResponseDto("삭제 성공", HttpStatus.OK);
    }

    public NewResponseDto deleteMovieAward(Long seq) {
        MovieAwardConnectionEntity awardConnection = mAwardRepo.findById(seq).orElseThrow(()-> new NotFoundEntityException("영화 상"));
        mAwardRepo.delete(awardConnection);
        return new NewResponseDto("삭제 성공", HttpStatus.OK);
    }

    public NewResponseDto deleteMovieCreator(Long seq) {
        CreatorMovieConnectionEntity creatorConnection = cMovieRepo.findById(seq).orElseThrow(()-> new NotFoundEntityException("영화-영화인"));
        cMovieRepo.delete(creatorConnection);

        return new NewResponseDto("삭제 성공", HttpStatus.OK);
    }

    public NewResponseDto updateMovieRole(Long seq, MovieRole role){
        CreatorMovieConnectionEntity creatorConnection = cMovieRepo.findById(seq).orElseThrow(()-> new NotFoundEntityException("영화-영화인"));
        creatorConnection.updateRole(role);
        cMovieRepo.save(creatorConnection);
        return new NewResponseDto("수정 성공", HttpStatus.OK);
    }
}
