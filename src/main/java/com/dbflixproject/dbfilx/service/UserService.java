package com.dbflixproject.dbfilx.service;

import com.dbflixproject.dbfilx.dto.NewResponseDataDto;
import com.dbflixproject.dbfilx.dto.NewResponseDto;
import com.dbflixproject.dbfilx.dto.user.UserDetailDto;
import com.dbflixproject.dbfilx.dto.user.UserJoinDto;
import com.dbflixproject.dbfilx.dto.user.UserUpdateDto;
import com.dbflixproject.dbfilx.entity.user.UserInfoEntity;
import com.dbflixproject.dbfilx.exception.NotFoundEntityException;
import com.dbflixproject.dbfilx.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserInfoRepository userRepo;
    private final FileService fileService;

    @Transactional
    public NewResponseDto userJoin(UserJoinDto data, MultipartFile file){
        if(userRepo.existsByUiId(data.getId())){
            return NewResponseDto.fail("이미 등록된 아이디입니다.");
        }
        UserInfoEntity user = new UserInfoEntity();
        if(file!=null){
            String fileName = fileService.saveImageFile(file);
            user.fileSetting(fileName, file.getOriginalFilename());
        }
        user.joinData(data.getId(), data.getPwd(), data.getName(), data.getEmail(), data.getBirth(), data.getGen());

        userRepo.save(user);

        return NewResponseDto.success("회원가입");
    }

    @Transactional(readOnly = true)
    public NewResponseDataDto<UserDetailDto> getUserDetail(Long id){
        UserInfoEntity user = userRepo.findByUiSeqAndUiStatus(id, true).orElseThrow(()->new NotFoundEntityException("회원"));

        UserDetailDto userDto = new UserDetailDto(user);
        return NewResponseDataDto.success("조회", userDto);
    }

    @Transactional
    public NewResponseDto updateUserInfo(Long id, UserUpdateDto data, MultipartFile file){
        UserInfoEntity user = userRepo.findByUiSeqAndUiStatus(id, true).orElseThrow(()->new NotFoundEntityException("회원"));

        if(!user.getUiPwd().equals(data.getOriginPwd())){
            return NewResponseDto.fail("비밀번호 오류");
        }
        if(file!=null){
            String fileName = fileService.saveImageFile(file);
            user.fileSetting(fileName, file.getOriginalFilename());
        }
        user.changeUserInfo(data.getChangePwd(), data.getEmail(), data.getName(), data.getGen());

        return NewResponseDto.success("수정");
    }

    @Transactional
    public NewResponseDto dropUser(Long id){
        UserInfoEntity user = userRepo.findByUiSeqAndUiStatus(id, true).orElseThrow(()->new NotFoundEntityException("회원"));
        user.dropUser();
//        userRepo.save(user);

        return NewResponseDto.success("탈퇴");
    }
}
