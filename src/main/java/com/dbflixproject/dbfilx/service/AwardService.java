package com.dbflixproject.dbfilx.service;

import com.dbflixproject.dbfilx.dto.NewResponseDto;
import com.dbflixproject.dbfilx.dto.award.AwardInsertDto;
import com.dbflixproject.dbfilx.dto.ResponseDto;
import com.dbflixproject.dbfilx.dto.award.AwardUpdateDto;
import com.dbflixproject.dbfilx.entity.AwardInfoEntity;
import com.dbflixproject.dbfilx.exception.NotFoundEntityException;
import com.dbflixproject.dbfilx.repository.AwardInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AwardService {
    private final AwardInfoRepository awardRepo;

    @Transactional
    public NewResponseDto insertAward(AwardInsertDto data){
        AwardInfoEntity award = new AwardInfoEntity(data.getName(), data.getYear(), data.getCategory());
        awardRepo.save(award);

        return NewResponseDto.builder().code(HttpStatus.OK).time(LocalDateTime.now()).message("조회 성공").build();
    }

    @Transactional
    public NewResponseDto updateAward(Long seq, AwardUpdateDto data) {
        AwardInfoEntity award = awardRepo.findById(seq).orElseThrow(() -> new NotFoundEntityException("상"));
        award.updateData(data.getName(), data.getYear(), data.getCategory());
        return NewResponseDto.builder().code(HttpStatus.OK).time(LocalDateTime.now()).message("수정 성공").build();
    }
    @Transactional
    public NewResponseDto deleteAward(Long seq){
        AwardInfoEntity award = awardRepo.findById(seq).orElseThrow(() -> new NotFoundEntityException("상"));
        awardRepo.delete(award);
        return NewResponseDto.builder().code(HttpStatus.OK).time(LocalDateTime.now()).message("조회 성공").build();
    }
}
