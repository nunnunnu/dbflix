package com.dbflixproject.dbfilx.api;

import com.dbflixproject.dbfilx.dto.ResponseDto;
import com.dbflixproject.dbfilx.dto.creator.CreatorDetailDto;
import com.dbflixproject.dbfilx.dto.creator.CreatorInsertDto;
import com.dbflixproject.dbfilx.dto.creator.CreatorUpdateDto;
import com.dbflixproject.dbfilx.service.CreatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/creator")
public class CreatorApiController {
    private final CreatorService creatorService;

    @PostMapping()
    public ResponseEntity<ResponseDto> insertCreator(@RequestBody CreatorInsertDto data){
        ResponseDto response = creatorService.saveCreator(data);
        return  new ResponseEntity<>(response, response.getCode());
    }
    @GetMapping("{seq}")
    public ResponseEntity<ResponseDto<CreatorDetailDto>> getCreator(@PathVariable Long seq){
        ResponseDto<CreatorDetailDto> response = creatorService.getCreatorDetail(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @PostMapping("/award/{creatorSeq}/{awardSeq}")
    public ResponseEntity<ResponseDto> addAward(@PathVariable Long creatorSeq, @PathVariable Long awardSeq){
        ResponseDto response = creatorService.addCreatorAward(creatorSeq, awardSeq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @PutMapping("/{seq}")
    public ResponseEntity<ResponseDto> updateCreatorInfo(@PathVariable Long seq, @RequestBody CreatorUpdateDto data){
        ResponseDto response = creatorService.updateCreatorInfo(seq, data);
        return new ResponseEntity(response, response.getCode());
    }

}
