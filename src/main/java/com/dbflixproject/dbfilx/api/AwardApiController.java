package com.dbflixproject.dbfilx.api;

import com.dbflixproject.dbfilx.dto.NewResponseDto;
import com.dbflixproject.dbfilx.dto.award.AwardInsertDto;
import com.dbflixproject.dbfilx.dto.award.AwardUpdateDto;
import com.dbflixproject.dbfilx.service.AwardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/award")
@Tag(name="상")
public class AwardApiController {
    private final AwardService awardService;

    @Operation(summary = "상 등록")
    @PostMapping
    public ResponseEntity<NewResponseDto> insertAward(@Valid @RequestBody AwardInsertDto data){
        NewResponseDto response = awardService.insertAward(data);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "상 정보 수정")
    @PutMapping("/{seq}")
    public ResponseEntity<NewResponseDto> updateAward(@PathVariable Long seq, @Valid @RequestBody AwardUpdateDto data){
        NewResponseDto response = awardService.updateAward(seq, data);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "상 삭제")
    @DeleteMapping("/{seq}")
    public ResponseEntity<NewResponseDto> deleteAward(@PathVariable Long seq){
        NewResponseDto response = awardService.deleteAward(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
}
