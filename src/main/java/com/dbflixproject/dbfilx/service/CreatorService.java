package com.dbflixproject.dbfilx.service;

import java.time.LocalDateTime;
import java.util.List;

import com.dbflixproject.dbfilx.dto.NewResponseDataDto;
import com.dbflixproject.dbfilx.dto.NewResponseDto;
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
    public NewResponseDto saveCreator(CreatorInsertDto data){
        CreatorInfoEntity creator = new CreatorInfoEntity(data.getName(), data.getGen(), data.getType(), data.getAge(), data.getCountry());
        creatorRepo.save(creator);
        return new NewResponseDto("등록 성공",HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    public NewResponseDataDto<CreatorDetailDto> getCreatorDetail(Long id){
        CreatorInfoEntity creator = creatorRepo.findById(id).orElseThrow(()->new NotFoundEntityException("영화인"));
        List< CreatorAwardConnectionEntity> awardConnection = cAwardRepo.findByCreator(creator);
        List<CreatorMovieConnectionEntity> movieConnection = cMovieRepo.findByCreator(creator);

        CreatorDetailDto result = new CreatorDetailDto(creator, awardConnection, movieConnection);

        return new NewResponseDataDto<>("조회 성공", HttpStatus.OK, result);
    }
    @Transactional
    public NewResponseDto addCreatorAward(Long creatorSeq, Long awardSeq){
        CreatorInfoEntity creator = creatorRepo.findById(creatorSeq).orElseThrow(()->new NotFoundEntityException("영화인"));
        AwardInfoEntity award = awardRepo.findById(awardSeq).orElseThrow(()->new NotFoundEntityException("상"));
        if(!award.getAiCate().toString().equals(creator.getCiRole().toString())){
            return new NewResponseDto(award.getAiCate()+"타입의 상을 "+creator.getCiRole()+"타입의 영화인에게 등록할 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
        if(cAwardRepo.existsByCreatorAndAward(creator, award)){
            return new NewResponseDto("이미 등록된 상입니다.", HttpStatus.BAD_REQUEST);
        }
        CreatorAwardConnectionEntity connect = new CreatorAwardConnectionEntity(null, creator, award);
        cAwardRepo.save(connect);

        return new NewResponseDto("등록 성공", HttpStatus.OK);
    }
    @Transactional
    public NewResponseDto updateCreatorInfo(Long seq, CreatorUpdateDto data){
        CreatorInfoEntity creator = creatorRepo.findById(seq).orElseThrow(()->new NotFoundEntityException("영화인"));
        creator.updateCreatorData(data.getName(), data.getCountry(), data.getAge(), data.getGen());
        creatorRepo.save(creator);

        return new NewResponseDto("수정 성공", HttpStatus.OK);
    }
    @Transactional
    public NewResponseDto deleteCreator(Long seq) {
        CreatorInfoEntity creator = creatorRepo.findById(seq).orElseThrow(()->new NotFoundEntityException("영화인"));
        creatorRepo.delete(creator);

        return new NewResponseDto("삭제 성공", HttpStatus.OK);
    }
    @Transactional
    public NewResponseDto deleteCreatorAward(Long seq) {
        CreatorAwardConnectionEntity awardConnection = cAwardRepo.findById(seq).orElseThrow(()-> new NotFoundEntityException("영화인 상"));
        cAwardRepo.delete(awardConnection);

        return new NewResponseDto("삭제 성공", HttpStatus.OK);
    }
}
