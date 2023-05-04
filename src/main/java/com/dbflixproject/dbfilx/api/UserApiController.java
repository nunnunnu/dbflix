package com.dbflixproject.dbfilx.api;

import com.dbflixproject.dbfilx.dto.NewResponseDataDto;
import com.dbflixproject.dbfilx.dto.NewResponseDto;
import com.dbflixproject.dbfilx.dto.user.UserDetailDto;
import com.dbflixproject.dbfilx.dto.user.UserJoinDto;
import com.dbflixproject.dbfilx.dto.user.UserUpdateDto;
import com.dbflixproject.dbfilx.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name="회원")
public class UserApiController {
    private final UserService userService;

    @Operation(summary = "회원가입")
    @PostMapping
    public ResponseEntity<NewResponseDto> userJoin(@Valid UserJoinDto data, @Nullable MultipartFile file){
        NewResponseDto response = userService.userJoin(data, file);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "회원정보 조회")
    @GetMapping("/{seq}")
    public ResponseEntity<NewResponseDataDto<UserDetailDto>> getUserInfo(@PathVariable Long seq){
        NewResponseDataDto<UserDetailDto> response = userService.getUserDetail(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "회원정보 수정")
    @PutMapping("/{seq}")
    public ResponseEntity<NewResponseDto> updateUserInfo(@PathVariable Long seq, @Valid UserUpdateDto data, @Nullable MultipartFile file){
        NewResponseDto response = userService.updateUserInfo(seq, data, file);
        return new ResponseEntity<>(response, response.getCode());
    }
    @Operation(summary = "회원 탈퇴")
    @PatchMapping("/{seq}")
    public ResponseEntity<NewResponseDto> dropUser(@PathVariable Long seq){
        NewResponseDto response = userService.dropUser(seq);
        return new ResponseEntity<>(response, response.getCode());
    }
}
