package com.dbflixproject.dbfilx.api;

import com.dbflixproject.dbfilx.dto.ResponseDto;
import com.dbflixproject.dbfilx.dto.company.CompanyDetailDto;
import com.dbflixproject.dbfilx.dto.company.CompanyInsertDto;
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
    public ResponseEntity<ResponseDto> insertCompany(@RequestBody @Valid CompanyInsertDto data){
        ResponseDto response = companyService.insertCompany(data);
        return new ResponseEntity<>(response, response.getCode());
    }
    @GetMapping("/{seq}")
    public ResponseEntity<ResponseDto<CompanyDetailDto>> getCompanyDetailInfo(@PathVariable Long seq){
        ResponseDto response = companyService.getCompanyDetail(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
}
