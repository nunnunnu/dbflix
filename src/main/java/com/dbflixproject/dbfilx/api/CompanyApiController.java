package com.dbflixproject.dbfilx.api;

import com.dbflixproject.dbfilx.dto.NewResponseDataDto;
import com.dbflixproject.dbfilx.dto.NewResponseDto;
import com.dbflixproject.dbfilx.dto.company.CompanyDetailDto;
import com.dbflixproject.dbfilx.dto.company.CompanyInsertDto;
import com.dbflixproject.dbfilx.dto.company.CompanyUpdateDto;
import com.dbflixproject.dbfilx.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
@Tag(name="제작사")
public class CompanyApiController {
    private final CompanyService companyService;

    @Operation(summary = "제작사 등록")
    @PostMapping
    public ResponseEntity<NewResponseDto> insertCompany(@RequestBody @Valid CompanyInsertDto data){
        NewResponseDto response = companyService.insertCompany(data);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "제작사 정보 조회")
    @GetMapping("/{seq}")
    public ResponseEntity<NewResponseDataDto<CompanyDetailDto>> getCompanyDetailInfo(@PathVariable Long seq){
        NewResponseDataDto<CompanyDetailDto> response = companyService.getCompanyDetail(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "제작사 정보 삭제")
    @DeleteMapping("/{seq}")
    public ResponseEntity<NewResponseDto> deleteCompany(@PathVariable Long seq){
        NewResponseDto response = companyService.deleteCompany(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "제작사 정보 수정")
    @PutMapping("/{seq}")
    public ResponseEntity<NewResponseDto> updateCompany(@PathVariable Long seq, @RequestBody @Valid CompanyUpdateDto data){
        NewResponseDto response = companyService.updateCompany(seq, data);
        return new ResponseEntity<>(response, response.getCode());
    }
}