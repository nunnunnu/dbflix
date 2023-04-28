package com.dbflixproject.dbfilx.service;

import com.dbflixproject.dbfilx.dto.ResponseDto;
import com.dbflixproject.dbfilx.dto.company.CompanyDetailDto;
import com.dbflixproject.dbfilx.dto.company.CompanyInsertDto;
import com.dbflixproject.dbfilx.dto.company.CompanyUpdateDto;
import com.dbflixproject.dbfilx.entity.CompanyInfoEntity;
import com.dbflixproject.dbfilx.entity.movie.MovieInfoEntity;
import com.dbflixproject.dbfilx.exception.NotFoundEntityException;
import com.dbflixproject.dbfilx.repository.CompanyInfoRepository;
import com.dbflixproject.dbfilx.repository.MovieInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyInfoRepository companyRepo;
    private final MovieInfoRepository movieRepo;

    @Transactional
    public ResponseDto<?> insertCompany(CompanyInsertDto data){
        if(companyRepo.existsByComBusinessNum(data.getBusinessNum())){
            return new ResponseDto.FailBuilder<>("이미 등록된 사업자 번호").build();
        }
        CompanyInfoEntity company = new CompanyInfoEntity(data.getBusinessNum(),data.getName(),data.getAddress());
        companyRepo.save(company);

        return new ResponseDto.SuccessBuilder<>("등록 성공", null).build();
    }
    @Transactional(readOnly = true)
    public ResponseDto<CompanyDetailDto> getCompanyDetail(Long seq){
        CompanyInfoEntity company = companyRepo.findById(seq).orElseThrow(()-> new NotFoundEntityException("제작사"));
        List<MovieInfoEntity> movies = movieRepo.findByCompany(company);

        CompanyDetailDto result = new CompanyDetailDto(company, movies);

        return new ResponseDto.SuccessBuilder<>("조회 성공", result).build();

    }
    @Transactional
    public ResponseDto<?> deleteCompany(Long seq) {
        CompanyInfoEntity company = companyRepo.findById(seq).orElseThrow(()-> new NotFoundEntityException("제작사"));
        companyRepo.delete(company);
        return new ResponseDto.SuccessBuilder<>("삭제 성공", null).build();
    }
    @Transactional
    public ResponseDto<?> updateCompany(Long seq, CompanyUpdateDto data){
        CompanyInfoEntity company = companyRepo.findById(seq).orElseThrow(()-> new NotFoundEntityException("제작사"));
        company.updateData(data.getBusinessNum(),data.getName(),data.getAddress());
        companyRepo.save(company);
        return new ResponseDto.SuccessBuilder<>("수정 성공", null).build();
    }
}
