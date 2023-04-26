package com.dbflixproject.dbfilx.service;

import com.dbflixproject.dbfilx.dto.ResponseDto;
import com.dbflixproject.dbfilx.dto.company.CompanyDetailDto;
import com.dbflixproject.dbfilx.dto.company.CompanyInsertDto;
import com.dbflixproject.dbfilx.entity.CompanyInfoEntity;
import com.dbflixproject.dbfilx.entity.movie.MovieInfoEntity;
import com.dbflixproject.dbfilx.exception.NotFoundCompanyException;
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
            return ResponseDto.builder().status(false).code(HttpStatus.BAD_REQUEST).message("이미 등록된 사업자번호").time(LocalDateTime.now()).build();
        }
        CompanyInfoEntity company = new CompanyInfoEntity(data.getBusinessNum(),data.getName(),data.getAddress());
        companyRepo.save(company);

        return ResponseDto.builder().status(true).code(HttpStatus.OK).message("등록 성공").time(LocalDateTime.now()).build();
    }
    @Transactional(readOnly = true)
    public ResponseDto<CompanyDetailDto> getCompanyDetail(Long seq){
        CompanyInfoEntity company = companyRepo.findById(seq).orElseThrow(()-> new NotFoundCompanyException());
        List<MovieInfoEntity> movies = movieRepo.findByCompany(company);

        CompanyDetailDto result = new CompanyDetailDto(company, movies);

        return new ResponseDto<CompanyDetailDto>("조회성공",LocalDateTime.now(),true,result,HttpStatus.OK);

    }
}
