package com.dbflixproject.dbfilx.service;

import com.dbflixproject.dbfilx.dto.award.AwardInsertDto;
import com.dbflixproject.dbfilx.dto.ResponseDto;
import com.dbflixproject.dbfilx.dto.award.AwardUpdateDto;
import com.dbflixproject.dbfilx.entity.AwardInfoEntity;
import com.dbflixproject.dbfilx.exception.NotFoundEntityException;
import com.dbflixproject.dbfilx.repository.AwardInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AwardService {
    private final AwardInfoRepository awardRepo;

    @Transactional
    public ResponseDto<?> insertAward(AwardInsertDto data){
        AwardInfoEntity award = new AwardInfoEntity(data.getName(), data.getYear(), data.getCategory());
        awardRepo.save(award);

        return new ResponseDto.SuccessBuilder<>("등록 성공", null).build();
    }

    @Transactional
    public ResponseDto<?> updateAward(Long seq, AwardUpdateDto data) {
        AwardInfoEntity award = awardRepo.findById(seq).orElseThrow(() -> new NotFoundEntityException("상"));
        award.updateData(data.getName(), data.getYear(), data.getCategory());
        return new ResponseDto.SuccessBuilder<>("수정 성공", null).build();
    }
    @Transactional
    public ResponseDto<?> deleteAward(Long seq){
        AwardInfoEntity award = awardRepo.findById(seq).orElseThrow(() -> new NotFoundEntityException("상"));
        awardRepo.delete(award);
        return new ResponseDto.SuccessBuilder<>("삭제 성공", null).build();
    }
}
