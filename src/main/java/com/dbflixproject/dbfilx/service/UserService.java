package com.dbflixproject.dbfilx.service;

import com.dbflixproject.dbfilx.dto.ResponseDto;
import com.dbflixproject.dbfilx.dto.user.UserDetailDto;
import com.dbflixproject.dbfilx.dto.user.UserJoinDto;
import com.dbflixproject.dbfilx.dto.user.UserUpdateDto;
import com.dbflixproject.dbfilx.entity.user.UserInfoEntity;
import com.dbflixproject.dbfilx.exception.NotFoundEntityException;
import com.dbflixproject.dbfilx.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserInfoRepository userRepo;
    private final FileService fileService;

    @Transactional
    public ResponseDto<?> userJoin(UserJoinDto data, MultipartFile file){
        if(userRepo.existsByUiId(data.getId())){
            return new ResponseDto.FailBuilder<>("이미 등록된 아이디입니다.").build();
        }
        UserInfoEntity user = new UserInfoEntity();
        if(file!=null){
            String fileName = fileService.saveImageFile(file);
            user.fileSetting(fileName, file.getOriginalFilename());
        }
        user.joinData(data.getId(), data.getPwd(), data.getName(), data.getEmail(), data.getBirth(), data.getGen());

        userRepo.save(user);

        return new ResponseDto.SuccessBuilder<>("회원가입 성공", null).build();
    }

    @Transactional(readOnly = true)
    public ResponseDto<UserDetailDto> getUserDetail(Long id){
        UserInfoEntity user = userRepo.findByUiSeqAndUiStatus(id, true).orElseThrow(()->new NotFoundEntityException("회원"));

        UserDetailDto userDto = new UserDetailDto(user);
        return new ResponseDto.SuccessBuilder<>("조회 성공", userDto).build();
    }

    @Transactional
    public ResponseDto<?> updateUserInfo(Long id, UserUpdateDto data, MultipartFile file){
        UserInfoEntity user = userRepo.findByUiSeqAndUiStatus(id, true).orElseThrow(()->new NotFoundEntityException("회원"));

        if(!user.getUiPwd().equals(data.getOriginPwd())){
            return new ResponseDto.FailBuilder<>("비밀번호 오류").build();
        }
        if(file!=null){
            String fileName = fileService.saveImageFile(file);
            user.fileSetting(fileName, file.getOriginalFilename());
        }
        user.changeUserInfo(data.getChangePwd(), data.getEmail(), data.getName(), data.getGen());

        return new ResponseDto.SuccessBuilder<>("변경 성공", null).build();
    }

    @Transactional
    public ResponseDto<?> dropUser(Long id){
        UserInfoEntity user = userRepo.findByUiSeqAndUiStatus(id, true).orElseThrow(()->new NotFoundEntityException("회원"));
        user.dropUser();
        userRepo.save(user);

        return new ResponseDto.SuccessBuilder<>("탈퇴 성공", null).build();
    }





}
