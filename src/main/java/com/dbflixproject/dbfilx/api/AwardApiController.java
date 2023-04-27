package com.dbflixproject.dbfilx.api;

import com.dbflixproject.dbfilx.dto.award.AwardInsertDto;
import com.dbflixproject.dbfilx.dto.ResponseDto;
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

    @PostMapping()
    public ResponseEntity<ResponseDto<?>> insertAward(@Valid @RequestBody AwardInsertDto data){
        ResponseDto<?> response = awardService.insertAward(data);
        return new ResponseEntity<>(response, response.getCode());
    }
    @PutMapping("/{seq}")
    public ResponseEntity<ResponseDto<?>> updateAward(@PathVariable Long seq, @Valid @RequestBody AwardUpdateDto data){
        ResponseDto<?> response = awardService.updateAward(seq, data);
        return new ResponseEntity<>(response, response.getCode());
    }
    @DeleteMapping("/{seq}")
    public ResponseEntity<?> deleteAward(@PathVariable Long seq){
        ResponseDto<?> response = awardService.deleteAward(seq);
        return new ResponseEntity<>(response, response.getCode());
    }

}
