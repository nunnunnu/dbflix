package com.dbflixproject.dbfilx.api;

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
    public ResponseEntity<ResponseDto<?>> insertCompany(@RequestBody @Valid CompanyInsertDto data){
        ResponseDto<?> response = companyService.insertCompany(data);
        return new ResponseEntity<>(response, response.getCode());
    }
    @GetMapping("/{seq}")
    public ResponseEntity<ResponseDto<CompanyDetailDto>> getCompanyDetailInfo(@PathVariable Long seq){
        ResponseDto<CompanyDetailDto> response = companyService.getCompanyDetail(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @DeleteMapping("/{seq}")
    public ResponseEntity<ResponseDto<?>> deleteCompany(@PathVariable Long seq){
        ResponseDto<?> response = companyService.deleteCompany(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @PutMapping("/{seq}")
    public ResponseEntity<ResponseDto> updateCompany(@PathVariable Long seq, @RequestBody @Valid CompanyUpdateDto data){
        ResponseDto<?> response = companyService.updateCompany(seq, data);
        return new ResponseEntity<>(response, response.getCode());
    }
}
