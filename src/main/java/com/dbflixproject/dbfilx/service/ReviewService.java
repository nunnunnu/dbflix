package com.dbflixproject.dbfilx.service;

import java.time.LocalDateTime;
import java.util.List;

import com.dbflixproject.dbfilx.dto.review.FavoriteGenreDto;
import com.dbflixproject.dbfilx.exception.NotFoundEntityException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbflixproject.dbfilx.dto.ResponseDto;
import com.dbflixproject.dbfilx.dto.review.ReviewDetailDto;
import com.dbflixproject.dbfilx.dto.review.ReviewInsertDto;
import com.dbflixproject.dbfilx.entity.ReviewInfoEntity;
import com.dbflixproject.dbfilx.entity.movie.MovieInfoEntity;
import com.dbflixproject.dbfilx.entity.user.UserInfoEntity;
import com.dbflixproject.dbfilx.exception.NotFoundMemberException;
import com.dbflixproject.dbfilx.exception.NotFoundMovieException;
import com.dbflixproject.dbfilx.repository.MovieInfoRepository;
import com.dbflixproject.dbfilx.repository.ReviewInfoRepository;
import com.dbflixproject.dbfilx.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewInfoRepository reviewRepo;
    private final UserInfoRepository userRepo;
    private final MovieInfoRepository movieRepo;

    @Transactional
    public ResponseDto<?> insertReview(ReviewInsertDto data){
        UserInfoEntity user = userRepo.findByUiSeqAndUiStatus(data.getUserSeq(), true);
        if(user==null){
            throw new NotFoundMemberException();
        }
        MovieInfoEntity movie = movieRepo.findById(data.getMovieSeq()).orElseThrow(()->new NotFoundMovieException());

        if(reviewRepo.existsByUserAndMovie(user, movie)){
            return ResponseDto.builder().time(LocalDateTime.now()).message("이미 등록된 리뷰가 존재합니다.").code(HttpStatus.BAD_REQUEST).status(false).build();
        }

        ReviewInfoEntity review = new ReviewInfoEntity(data, user, movie);
        reviewRepo.save(review);

        return ResponseDto.builder().status(true).code(HttpStatus.OK).message("등록 성공").time(LocalDateTime.now()).build();
    }
    @Transactional(readOnly = true)
    public ResponseDto<Page<ReviewDetailDto>> getReview(Long seq, Pageable pageable){
        MovieInfoEntity movie = movieRepo.findById(seq).orElseThrow(() -> new NotFoundMovieException());

        Page<ReviewInfoEntity> reviewEntity = reviewRepo.findByMovie(movie, pageable);
        Page<ReviewDetailDto> result = reviewEntity.map(r->new ReviewDetailDto(r));
        return new ResponseDto<>("조회성공", LocalDateTime.now(), true, result, HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    public ResponseDto<List<FavoriteGenreDto>> myFavoriteGenre(Long seq){
        List<FavoriteGenreDto> result = reviewRepo.favoriteGenre(seq);
        return new ResponseDto<>("조회성공", LocalDateTime.now(), true, result, HttpStatus.OK);

    }
    @Transactional
    public ResponseDto<?> deleteReview(Long seq) {
        ReviewInfoEntity review = reviewRepo.findById(seq).orElseThrow(()-> new NotFoundEntityException("리뷰"));
        reviewRepo.delete(review);
        return ResponseDto.builder().message("삭제성공").time(LocalDateTime.now()).status(true).code(HttpStatus.OK).build();
    }
}
