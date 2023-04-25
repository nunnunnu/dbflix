package com.dbflixproject.dbfilx.service;

import com.dbflixproject.dbfilx.dto.ResponseDto;
import com.dbflixproject.dbfilx.dto.creator.CreatorDetailDto;
import com.dbflixproject.dbfilx.dto.creator.CreatorInsertDto;
import com.dbflixproject.dbfilx.entity.AwardInfoEntity;
import com.dbflixproject.dbfilx.entity.creator.CreatorAwardConnectionEntity;
import com.dbflixproject.dbfilx.entity.creator.CreatorInfoEntity;
import com.dbflixproject.dbfilx.entity.creator.CreatorMovieConnectionEntity;
import com.dbflixproject.dbfilx.exception.NotFoundCreatorException;
import com.dbflixproject.dbfilx.repository.CreatorAwardConnectionRepository;
import com.dbflixproject.dbfilx.repository.CreatorInfoRepository;
import com.dbflixproject.dbfilx.repository.CreatorMovieConnectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreatorService {
    private final CreatorInfoRepository creatorRepo;
    private final CreatorMovieConnectionRepository cMovieRepo;
    private final CreatorAwardConnectionRepository cAwardRepo;

    @Transactional
    public ResponseDto saveCreator(CreatorInsertDto data){
        CreatorInfoEntity creator = new CreatorInfoEntity(data);
        creatorRepo.save(creator);
        return ResponseDto.builder().code(HttpStatus.OK).status(true).time(LocalDateTime.now()).message("등록성공").build();
    }

    @Transactional(readOnly = true)
    public ResponseDto<CreatorDetailDto> getCreatorDetail(Long id){
        CreatorInfoEntity creator = creatorRepo.findById(id).orElseThrow(()->new NotFoundCreatorException());
        List< CreatorAwardConnectionEntity> awardConnection = cAwardRepo.findByCreator(creator);
        List<CreatorMovieConnectionEntity> movieConnection = cMovieRepo.findByCreator(creator);

        CreatorDetailDto result = new CreatorDetailDto(creator, awardConnection, movieConnection);

        return new ResponseDto<CreatorDetailDto>("조회성공", LocalDateTime.now(), true, result, HttpStatus.OK);
    }


}
