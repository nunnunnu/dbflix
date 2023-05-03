package com.dbflixproject.dbfilx.api;

import com.dbflixproject.dbfilx.dto.NewResponseDto;
import com.dbflixproject.dbfilx.dto.award.AwardInsertDto;
import com.dbflixproject.dbfilx.dto.award.AwardUpdateDto;
import com.dbflixproject.dbfilx.service.AwardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/award")
public class AwardApiController {
    private final AwardService awardService;

    @PostMapping
    public ResponseEntity<NewResponseDto> insertAward(@Valid @RequestBody AwardInsertDto data){
        NewResponseDto response = awardService.insertAward(data);
        return new ResponseEntity<>(response, response.getCode());
    }
    @PutMapping("/{seq}")
    public ResponseEntity<NewResponseDto> updateAward(@PathVariable Long seq, @Valid @RequestBody AwardUpdateDto data){
        NewResponseDto response = awardService.updateAward(seq, data);
        return new ResponseEntity<>(response, response.getCode());
    }
    @DeleteMapping("/{seq}")
    public ResponseEntity<NewResponseDto> deleteAward(@PathVariable Long seq){
        NewResponseDto response = awardService.deleteAward(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
}
