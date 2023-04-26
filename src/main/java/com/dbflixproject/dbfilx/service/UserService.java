package com.dbflixproject.dbfilx.service;

import com.dbflixproject.dbfilx.dto.ResponseDto;
import com.dbflixproject.dbfilx.dto.user.UserDetailDto;
import com.dbflixproject.dbfilx.dto.user.UserJoinDto;
import com.dbflixproject.dbfilx.dto.user.UserUpdateDto;
import com.dbflixproject.dbfilx.entity.user.UserInfoEntity;
import com.dbflixproject.dbfilx.exception.NotFoundMemberException;
import com.dbflixproject.dbfilx.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserInfoRepository userRepo;
    private final FileService fileService;

    @Transactional
    public ResponseDto<?> userJoin(UserJoinDto data, MultipartFile file){
        if(userRepo.existsByUiId(data.getId())){
            return ResponseDto.builder()
                    .time(LocalDateTime.now())
                    .status(false)
                    .message("이미 등록된 아이디입니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        UserInfoEntity user = new UserInfoEntity();
        if(file!=null){
            String fileName = fileService.saveImageFile(file);
            user.fileSetting(fileName, file.getOriginalFilename());
        }
        user.joinData(data.getId(), data.getPwd(), data.getName(), data.getEmail(), data.getBirth(), data.getGen());

        userRepo.save(user);

        return ResponseDto.builder().message("회원가입 성공").status(true).time(LocalDateTime.now()).code(HttpStatus.OK).build();
    }

    @Transactional(readOnly = true)
    public ResponseDto<UserDetailDto> getUserDetail(Long id){
        UserInfoEntity user = userRepo.findByUiSeqAndUiStatus(id, true);
        if(user==null){
            throw new NotFoundMemberException();
        }
        UserDetailDto userDto = new UserDetailDto(user);
        return new ResponseDto<>("조회성공", LocalDateTime.now(), true, userDto, HttpStatus.OK);
    }

    @Transactional
    public ResponseDto<?> updateUserInfo(Long id, UserUpdateDto data, MultipartFile file){
        UserInfoEntity user = userRepo.findByUiSeqAndUiStatus(id, true);
        if(user==null){
            throw new NotFoundMemberException();
        }
        if(!user.getUiPwd().equals(data.getOriginPwd())){
            return ResponseDto.builder().time(LocalDateTime.now()).message("비밀번호 오류").status(false).code(HttpStatus.BAD_REQUEST).build();
        }
        if(file!=null){
            String fileName = fileService.saveImageFile(file);
            user.fileSetting(fileName, file.getOriginalFilename());
        }
        user.changeUserInfo(data.getChangePwd(), data.getEmail(), data.getName(), data.getGen());

        return ResponseDto.builder().status(true).message("변경 성공").time(LocalDateTime.now()).code(HttpStatus.OK).build();
    }

    @Transactional
    public ResponseDto<?> dropUser(Long id){
        UserInfoEntity user = userRepo.findByUiSeqAndUiStatus(id, true);
        if(user==null){
            throw new NotFoundMemberException();
        }
        user.dropUser();
        userRepo.save(user);

        return ResponseDto.builder().status(true).message("탈퇴 성공").time(LocalDateTime.now()).code(HttpStatus.OK).build();
    }

}
