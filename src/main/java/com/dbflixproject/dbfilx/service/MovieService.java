package com.dbflixproject.dbfilx.service;

import com.dbflixproject.dbfilx.dto.NewResponseDataDto;
import com.dbflixproject.dbfilx.dto.NewResponseDto;
import com.dbflixproject.dbfilx.dto.movie.*;
import com.dbflixproject.dbfilx.entity.AwardInfoEntity;
import com.dbflixproject.dbfilx.entity.CompanyInfoEntity;
import com.dbflixproject.dbfilx.entity.creator.CreatorInfoEntity;
import com.dbflixproject.dbfilx.entity.creator.CreatorMovieConnectionEntity;
import com.dbflixproject.dbfilx.entity.enumfile.AwardCategory;
import com.dbflixproject.dbfilx.entity.enumfile.MovieRole;
import com.dbflixproject.dbfilx.entity.movie.MovieAwardConnectionEntity;
import com.dbflixproject.dbfilx.entity.movie.MovieInfoEntity;
import com.dbflixproject.dbfilx.exception.NotFoundEntityException;
import com.dbflixproject.dbfilx.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return NewResponseDto.success("등록");
    }
    @Transactional(readOnly = true)
    public NewResponseDataDto<MovieDetailDto> movieDetailShow(Long seq){
        MovieInfoEntity movie = movieRepo.findSeqCompanyjoin(seq).orElseThrow(()->new NotFoundEntityException("영화"));

        List<CreatorMovieConnectionEntity> creators = cMovieRepo.findByMovie(movie);
        List<MovieAwardConnectionEntity> awards = mAwardRepo.findByMovie(movie);
        Double rate = reviewRepo.movieRateAge(movie);
        MovieDetailDto result = new MovieDetailDto(movie, creators, awards, rate);

        return NewResponseDataDto.success("조회 성공", result);
    }

    @Transactional
    public NewResponseDto addCreator(MovieAddCreatorDto data){
        MovieInfoEntity movie = movieRepo.findById(data.getMovieSeq()).orElseThrow(()->new NotFoundEntityException("영화"));
        CreatorInfoEntity creator = creatorRepo.findById(data.getCreatorSeq()).orElseThrow(()->new NotFoundEntityException("제작사"));
        CreatorMovieConnectionEntity connect = new CreatorMovieConnectionEntity(null, creator, movie, data.getRole());
        if(cMovieRepo.existsByMovieAndCreator(movie, creator)){
            return NewResponseDto.fail("이미 등록된 영화");
        }
        cMovieRepo.save(connect);
        return NewResponseDto.success("영화인 추가");
    }
    @Transactional
    public NewResponseDto addAward(Long movieSeq, Long awardSeq){
        MovieInfoEntity movie = movieRepo.findById(movieSeq).orElseThrow(()->new NotFoundEntityException("영화"));
        AwardInfoEntity award = awardRepo.findByAiSeqAndAiCate(awardSeq, AwardCategory.영화).orElseThrow(()-> new NotFoundEntityException("상"));

        if(mAwardRepo.existsByMovieAndAward(movie, award)){
            return NewResponseDto.fail("이미 추가된 상");
        }
        MovieAwardConnectionEntity entity = new MovieAwardConnectionEntity(null, movie, award);
        mAwardRepo.save(entity);
        return NewResponseDto.success("등록");
    }
    @Transactional
    public NewResponseDto updateMovie(Long seq, MovieUpdateDto data){
        MovieInfoEntity movie = movieRepo.findById(seq).orElseThrow(()->new NotFoundEntityException("영화"));
        movie.changeData(data.getName(), data.getAttendance(), data.getRegDt(), data.getPrice(), data.getCounty(), data.getGenre());
        movieRepo.save(movie);

        return NewResponseDto.success("수정");
    }
    @Transactional(readOnly = true)
    public NewResponseDataDto<List<MovieRankingDto>> MovieRanking(String type){
        if(!type.equals("attendance") && !type.equals("rate")){
            return NewResponseDataDto.fail("타입 오류(attendance/rate)", null);
        }
        List<MovieRankingDto> movies = movieRepo.rateRanking(Sort.by(type).descending());

        return NewResponseDataDto.success("조회 성공", movies);
    }

    @Transactional
    public NewResponseDto movieDelete(Long seq) {
        MovieInfoEntity movie = movieRepo.findById(seq).orElseThrow(()-> new NotFoundEntityException("영화"));
        movieRepo.delete(movie);

        return NewResponseDto.success("삭제");
    }
    @Transactional
    public NewResponseDto deleteMovieAward(Long seq) {
        MovieAwardConnectionEntity awardConnection = mAwardRepo.findById(seq).orElseThrow(()-> new NotFoundEntityException("영화 상"));
        mAwardRepo.delete(awardConnection);
        return NewResponseDto.success("삭제");
    }
    @Transactional
    public NewResponseDto deleteMovieCreator(Long seq) {
        CreatorMovieConnectionEntity creatorConnection = cMovieRepo.findById(seq).orElseThrow(()-> new NotFoundEntityException("영화-영화인"));
        cMovieRepo.delete(creatorConnection);

        return NewResponseDto.success("삭제");
    }
    @Transactional
    public NewResponseDto updateMovieRole(Long seq, MovieRole role){
        CreatorMovieConnectionEntity creatorConnection = cMovieRepo.findById(seq).orElseThrow(()-> new NotFoundEntityException("영화-영화인"));
        creatorConnection.updateRole(role);
        cMovieRepo.save(creatorConnection);
        return NewResponseDto.success("수정");
    }
}
