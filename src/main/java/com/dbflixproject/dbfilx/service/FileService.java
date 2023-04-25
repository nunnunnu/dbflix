package com.dbflixproject.dbfilx.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
@Service
public class FileService {
    @Value("${file.image.profile}") String profile_img_path;

    @Transactional
    public String saveImageFile(MultipartFile file){
        Path folderLocation = Paths.get(profile_img_path);

        String originFileName = file.getOriginalFilename();
        String[] split = originFileName.split(("\\.")); //.을 기준으로 나눔
        String ext = split[split.length - 1]; //확장자
        String fileName = "";
        for (int i = 0; i < split.length - 1; i++) {
            fileName += split[i]; //원래 split[i]+"." 이렇게 해줘야함
        }
        String saveFileName = "profile_"; //보통 원본 이름을 저장하는것이아니라 시간대를 입력함
        Calendar c = Calendar.getInstance();
        saveFileName += c.getTimeInMillis() + "." + ext; // todo_161310135.png 이런식으로 저장됨

        Path targetFile = folderLocation.resolve(saveFileName); //폴더 경로와 파일의 이름을 합쳐서 목표 파일의 경로 생성
        try {
            //Files는 파일 처리에 대한 유틸리티 클래스
            //copy - 복사, file.getInputStream() - 파일을 열어서 파일의 내용을 읽는 준비
            //targetFile 경로로, standardCopyOption.REPLACE_EXISTING - 같은 파일이 있다면 덮어쓰기.
            Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saveFileName;
    }
}
