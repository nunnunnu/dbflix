package com.dbflixproject.dbfilx.api;

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

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<ResponseDto<?>> userJoin(@Valid UserJoinDto data, @Nullable MultipartFile file){
        return new ResponseEntity<>(userService.userJoin(data, file), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<ResponseDto<UserDetailDto>> getUserInfo(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUserDetail(id), HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<ResponseDto<?>> updateUserInfo(@PathVariable Long id, @Valid UserUpdateDto data, @Nullable MultipartFile file){
        ResponseDto<?> response = userService.updateUserInfo(id, data, file);
        return new ResponseEntity<>(response, response.getCode());
    }
    @PatchMapping("{id}")
    public ResponseEntity<ResponseDto<?>> dropUser(@PathVariable Long id){
        ResponseDto<?> response = userService.dropUser(id);
        return new ResponseEntity<>(response, response.getCode());
    }
}
