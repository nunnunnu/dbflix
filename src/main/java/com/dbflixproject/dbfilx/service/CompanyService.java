package com.dbflixproject.dbfilx.service;

import com.dbflixproject.dbfilx.dto.NewResponseDataDto;
import com.dbflixproject.dbfilx.dto.NewResponseDto;
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
    public NewResponseDto insertCompany(CompanyInsertDto data){
        if(companyRepo.existsByComBusinessNum(data.getBusinessNum())){
            return new NewResponseDto("이미 등록된 사업자번호", HttpStatus.BAD_REQUEST);
        }
        CompanyInfoEntity company = new CompanyInfoEntity(data.getBusinessNum(),data.getName(),data.getAddress());
        companyRepo.save(company);

        return new NewResponseDto("등록성공", HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    public NewResponseDataDto<CompanyDetailDto> getCompanyDetail(Long seq){
        CompanyInfoEntity company = companyRepo.findById(seq).orElseThrow(()-> new NotFoundEntityException("제작사"));
        List<MovieInfoEntity> movies = movieRepo.findByCompany(company);

        CompanyDetailDto result = new CompanyDetailDto(company, movies);

        return new NewResponseDataDto<>("조회성공", HttpStatus.OK, result);

    }
    @Transactional
    public NewResponseDto deleteCompany(Long seq) {
        CompanyInfoEntity company = companyRepo.findById(seq).orElseThrow(()-> new NotFoundEntityException("제작사"));
        companyRepo.delete(company);
        return new NewResponseDto("삭제 성공", HttpStatus.OK);
    }
    @Transactional
    public NewResponseDto updateCompany(Long seq, CompanyUpdateDto data){
        CompanyInfoEntity company = companyRepo.findById(seq).orElseThrow(()-> new NotFoundEntityException("제작사"));
        company.updateData(data.getBusinessNum(),data.getName(),data.getAddress());
        companyRepo.save(company);
        return new NewResponseDto("수정 성공", HttpStatus.OK);
    }
}
