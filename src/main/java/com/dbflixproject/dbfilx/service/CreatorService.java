package com.dbflixproject.dbfilx.service;

import java.time.LocalDateTime;
import java.util.List;

import com.dbflixproject.dbfilx.exception.NotFoundEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbflixproject.dbfilx.dto.ResponseDto;
import com.dbflixproject.dbfilx.dto.creator.CreatorDetailDto;
import com.dbflixproject.dbfilx.dto.creator.CreatorInsertDto;
import com.dbflixproject.dbfilx.dto.creator.CreatorUpdateDto;
import com.dbflixproject.dbfilx.entity.AwardInfoEntity;
import com.dbflixproject.dbfilx.entity.creator.CreatorAwardConnectionEntity;
import com.dbflixproject.dbfilx.entity.creator.CreatorInfoEntity;
import com.dbflixproject.dbfilx.entity.creator.CreatorMovieConnectionEntity;
import com.dbflixproject.dbfilx.exception.NotFoundEntityException;
import com.dbflixproject.dbfilx.repository.AwardInfoRepository;
import com.dbflixproject.dbfilx.repository.CreatorAwardConnectionRepository;
import com.dbflixproject.dbfilx.repository.CreatorInfoRepository;
import com.dbflixproject.dbfilx.repository.CreatorMovieConnectionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreatorService {
    private final CreatorInfoRepository creatorRepo;
    private final CreatorMovieConnectionRepository cMovieRepo;
    private final CreatorAwardConnectionRepository cAwardRepo;
    private final AwardInfoRepository awardRepo;

    @Transactional
    public ResponseDto<?> saveCreator(CreatorInsertDto data){
        CreatorInfoEntity creator = new CreatorInfoEntity(data);
        creatorRepo.save(creator);
        return new ResponseDto.SuccessBuilder<>("등록 성공", null).build();
    }
    @Transactional(readOnly = true)
    public ResponseDto<CreatorDetailDto> getCreatorDetail(Long id){
        CreatorInfoEntity creator = creatorRepo.findById(id).orElseThrow(()->new NotFoundEntityException("영화인"));
        List< CreatorAwardConnectionEntity> awardConnection = cAwardRepo.findByCreator(creator);
        List<CreatorMovieConnectionEntity> movieConnection = cMovieRepo.findByCreator(creator);

        CreatorDetailDto result = new CreatorDetailDto(creator, awardConnection, movieConnection);

        return new ResponseDto.SuccessBuilder<>("조회 성공", result).build();
    }
    @Transactional
    public ResponseDto<?> addCreatorAward(Long creatorSeq, Long awardSeq){
        CreatorInfoEntity creator = creatorRepo.findById(creatorSeq).orElseThrow(()->new NotFoundEntityException("영화인"));
        AwardInfoEntity award = awardRepo.findById(awardSeq).orElseThrow(()->new NotFoundEntityException("상"));
        if(!award.getAiCate().toString().equals(creator.getCiRole().toString())){
            return ResponseDto.builder()
                    .code(HttpStatus.BAD_REQUEST).status(false).time(LocalDateTime.now())
                    .message(award.getAiCate()+"타입의 상을 "+creator.getCiRole()+"타입의 영화인에게 등록할 수 없습니다.")
                    .build();
        }
        if(cAwardRepo.existsByCreatorAndAward(creator, award)){
            return ResponseDto.builder()
                    .code(HttpStatus.BAD_REQUEST).status(false).time(LocalDateTime.now())
                    .message("이미 등록된 상입니다.")
                    .build();
        }
        CreatorAwardConnectionEntity connect = new CreatorAwardConnectionEntity(null, creator, award);
        cAwardRepo.save(connect);

        return new ResponseDto.SuccessBuilder<>("등록 성공", null).build();
    }
    @Transactional
    public ResponseDto<?> updateCreatorInfo(Long seq, CreatorUpdateDto data){
        CreatorInfoEntity creator = creatorRepo.findById(seq).orElseThrow(()->new NotFoundEntityException("영화인"));
        creator.updateCreatorData(data.getName(), data.getCountry(), data.getAge(), data.getGen());
        creatorRepo.save(creator);

        return new ResponseDto.SuccessBuilder<>("수정 성공", null).build();
    }
    @Transactional
    public ResponseDto<?> deleteCreator(Long seq) {
        CreatorInfoEntity creator = creatorRepo.findById(seq).orElseThrow(()->new NotFoundEntityException("영화인"));
        creatorRepo.delete(creator);

        return new ResponseDto.SuccessBuilder<>("삭제 성공", null).build();
    }
    @Transactional
    public ResponseDto<?> deleteCreatorAward(Long seq) {
        CreatorAwardConnectionEntity awardConnection = cAwardRepo.findById(seq).orElseThrow(()-> new NotFoundEntityException("영화인 상"));
        cAwardRepo.delete(awardConnection);

        return new ResponseDto.SuccessBuilder<>("삭제 성공", null).build();
    }
}
