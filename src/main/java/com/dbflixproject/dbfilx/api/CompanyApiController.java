package com.dbflixproject.dbfilx.api;

import com.dbflixproject.dbfilx.dto.NewResponseDataDto;
import com.dbflixproject.dbfilx.dto.NewResponseDto;
import com.dbflixproject.dbfilx.dto.ResponseDto;
import com.dbflixproject.dbfilx.dto.company.CompanyDetailDto;
import com.dbflixproject.dbfilx.dto.company.CompanyInsertDto;
import com.dbflixproject.dbfilx.dto.company.CompanyUpdateDto;
import com.dbflixproject.dbfilx.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyApiController {
    private final CompanyService companyService;

    @PostMapping()
    public ResponseEntity<NewResponseDto> insertCompany(@RequestBody @Valid CompanyInsertDto data){
        NewResponseDto response = companyService.insertCompany(data);
        return new ResponseEntity<>(response, response.getCode());
    }
    @GetMapping("/{seq}")
    public ResponseEntity<NewResponseDataDto<CompanyDetailDto>> getCompanyDetailInfo(@PathVariable Long seq){
        NewResponseDataDto<CompanyDetailDto> response = companyService.getCompanyDetail(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @DeleteMapping("/{seq}")
    public ResponseEntity<NewResponseDto> deleteCompany(@PathVariable Long seq){
        NewResponseDto response = companyService.deleteCompany(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @PutMapping("/{seq}")
    public ResponseEntity<NewResponseDto> updateCompany(@PathVariable Long seq, @RequestBody @Valid CompanyUpdateDto data){
        NewResponseDto response = companyService.updateCompany(seq, data);
        return new ResponseEntity<>(response, response.getCode());
    }
}
