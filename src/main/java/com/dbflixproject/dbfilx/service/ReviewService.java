package com.dbflixproject.dbfilx.service;

import com.dbflixproject.dbfilx.dto.NewResponseDataDto;
import com.dbflixproject.dbfilx.dto.NewResponseDto;
import com.dbflixproject.dbfilx.dto.ResponseDto;
import com.dbflixproject.dbfilx.dto.review.FavoriteGenreDto;
import com.dbflixproject.dbfilx.dto.review.ReviewDetailDto;
import com.dbflixproject.dbfilx.dto.review.ReviewInsertDto;
import com.dbflixproject.dbfilx.entity.ReviewInfoEntity;
import com.dbflixproject.dbfilx.entity.movie.MovieInfoEntity;
import com.dbflixproject.dbfilx.entity.user.UserInfoEntity;
import com.dbflixproject.dbfilx.exception.NotFoundEntityException;
import com.dbflixproject.dbfilx.repository.MovieInfoRepository;
import com.dbflixproject.dbfilx.repository.ReviewInfoRepository;
import com.dbflixproject.dbfilx.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewInfoRepository reviewRepo;
    private final UserInfoRepository userRepo;
    private final MovieInfoRepository movieRepo;

    @Transactional
    public NewResponseDto insertReview(ReviewInsertDto data){
        UserInfoEntity user = userRepo.findByUiSeqAndUiStatus(data.getUserSeq(), true).orElseThrow(()->new NotFoundEntityException("회원"));

        MovieInfoEntity movie = movieRepo.findById(data.getMovieSeq()).orElseThrow(()->new NotFoundEntityException("영화"));

        if(reviewRepo.existsByUserAndMovie(user, movie)){
            return new NewResponseDto("이미 등록된 리뷰 존재", HttpStatus.BAD_REQUEST);
        }

        ReviewInfoEntity review = new ReviewInfoEntity(data.getComment(), data.getRating(), user, movie);
        reviewRepo.save(review);

        return new NewResponseDto("등록 성공", HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    public NewResponseDataDto<Page<ReviewDetailDto>> getReview(Long seq, Pageable pageable){
        MovieInfoEntity movie = movieRepo.findById(seq).orElseThrow(() -> new NotFoundEntityException("영화"));

        Page<ReviewInfoEntity> reviewEntity = reviewRepo.findByMovie(movie, pageable);
        Page<ReviewDetailDto> result = reviewEntity.map(ReviewDetailDto::new);
        return new NewResponseDataDto<>("조회 성공", HttpStatus.OK, result);
    }
    @Transactional(readOnly = true)
    public ResponseDto<List<FavoriteGenreDto>> myFavoriteGenre(Long seq){
        List<FavoriteGenreDto> result = reviewRepo.favoriteGenre(seq);
        return new ResponseDto.SuccessBuilder<>("조회 성공", result).build();

    }
    @Transactional
    public NewResponseDto deleteReview(Long seq) {
        ReviewInfoEntity review = reviewRepo.findById(seq).orElseThrow(()-> new NotFoundEntityException("리뷰"));
        reviewRepo.delete(review);
        return new NewResponseDto("삭제 성공", HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    public NewResponseDataDto<List<ReviewDetailDto>> myReview(Long seq){
        UserInfoEntity user = userRepo.findByUiSeqAndUiStatus(seq, true).orElseThrow(()->new NotFoundEntityException("회원"));

        List<ReviewInfoEntity> review = reviewRepo.findByUser(user);
//        if(review.size()==0){
//            return new ResponseDto<>("등록된 리뷰 없음", LocalDateTime.now(), false, null, HttpStatus.OK);
//        }
        List<ReviewDetailDto> reviewDto = review.stream().map(ReviewDetailDto::new).toList();

        return new NewResponseDataDto<>("조회 성공", HttpStatus.OK, reviewDto);
    }
}
