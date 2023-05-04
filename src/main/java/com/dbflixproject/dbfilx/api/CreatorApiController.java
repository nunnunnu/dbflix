package com.dbflixproject.dbfilx.api;

import com.dbflixproject.dbfilx.dto.NewResponseDataDto;
import com.dbflixproject.dbfilx.dto.NewResponseDto;
import com.dbflixproject.dbfilx.dto.creator.CreatorDetailDto;
import com.dbflixproject.dbfilx.dto.creator.CreatorInsertDto;
import com.dbflixproject.dbfilx.dto.creator.CreatorUpdateDto;
import com.dbflixproject.dbfilx.service.CreatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/creator")
@Tag(name="영화인")
public class CreatorApiController {
    private final CreatorService creatorService;

    @Operation(summary = "영화인 추가")
    @PostMapping
    public ResponseEntity<NewResponseDto> insertCreator(@RequestBody CreatorInsertDto data){
        NewResponseDto response = creatorService.saveCreator(data);
        return  new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "영화인 정보 조회")
    @GetMapping("/{seq}")
    public ResponseEntity<NewResponseDataDto<CreatorDetailDto>> getCreator(@PathVariable Long seq){
        NewResponseDataDto<CreatorDetailDto> response = creatorService.getCreatorDetail(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "영화인 수상내역 등록")
    @PostMapping("/award/{creatorSeq}/{awardSeq}")
    public ResponseEntity<NewResponseDto> addAward(@PathVariable Long creatorSeq, @PathVariable Long awardSeq){
        NewResponseDto response = creatorService.addCreatorAward(creatorSeq, awardSeq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "영화인 정보 수정")
    @PutMapping("/{seq}")
    public ResponseEntity<NewResponseDto> updateCreatorInfo(@PathVariable Long seq, @RequestBody CreatorUpdateDto data){
        NewResponseDto response = creatorService.updateCreatorInfo(seq, data);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "영화인 삭제")
    @DeleteMapping("/{seq}")
    public ResponseEntity<NewResponseDto> deleteCreator(@PathVariable Long seq){
        NewResponseDto response = creatorService.deleteCreator(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "영화인 수상내역 삭제")
    @DeleteMapping("/award/{seq}")
    public ResponseEntity<NewResponseDto> deleteCreatorAward(@PathVariable Long seq){
        NewResponseDto response = creatorService.deleteCreatorAward(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
}
