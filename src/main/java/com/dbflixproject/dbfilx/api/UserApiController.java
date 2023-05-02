package com.dbflixproject.dbfilx.api;

import com.dbflixproject.dbfilx.dto.NewResponseDataDto;
import com.dbflixproject.dbfilx.dto.NewResponseDto;
import com.dbflixproject.dbfilx.dto.review.ReviewDetailDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dbflixproject.dbfilx.dto.ResponseDto;
import com.dbflixproject.dbfilx.dto.user.UserDetailDto;
import com.dbflixproject.dbfilx.dto.user.UserJoinDto;
import com.dbflixproject.dbfilx.dto.user.UserUpdateDto;
import com.dbflixproject.dbfilx.service.UserService;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<NewResponseDto> userJoin(@Valid UserJoinDto data, @Nullable MultipartFile file){
        NewResponseDto response = userService.userJoin(data, file);
        return new ResponseEntity<>(response, response.getCode());
    }
    @GetMapping("/{seq}")
    public ResponseEntity<NewResponseDataDto<UserDetailDto>> getUserInfo(@PathVariable Long seq){
        NewResponseDataDto response = userService.getUserDetail(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @PutMapping("/{seq}")
    public ResponseEntity<NewResponseDto> updateUserInfo(@PathVariable Long seq, @Valid UserUpdateDto data, @Nullable MultipartFile file){
        NewResponseDto response = userService.updateUserInfo(seq, data, file);
        return new ResponseEntity<>(response, response.getCode());
    }
    @PatchMapping("/{seq}")
    public ResponseEntity<NewResponseDto> dropUser(@PathVariable Long seq){
        NewResponseDto response = userService.dropUser(seq);
        return new ResponseEntity<>(response, response.getCode());
    }

}
