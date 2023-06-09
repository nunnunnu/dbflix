package com.dbflixproject.dbfilx.service;

import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;

import com.dbflixproject.dbfilx.exception.NotFoundFileException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.dbflixproject.dbfilx.entity.user.UserInfoEntity;
import com.dbflixproject.dbfilx.repository.UserInfoRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {
    @Value("${file.image.profile}") String profile_img_path;

    private final UserInfoRepository userRepo;

    @Transactional
    public String saveImageFile(MultipartFile file){
        Path folderLocation = Paths.get(profile_img_path);

        String originFileName = file.getOriginalFilename();
        String[] split = originFileName.split(("\\."));
        String ext = split[split.length - 1];
        String saveFileName = "profile_";
        Calendar c = Calendar.getInstance();
        saveFileName += c.getTimeInMillis() + "." + ext;
        Path targetFile = folderLocation.resolve(saveFileName);
        try {
            Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saveFileName;
    }

    public ResponseEntity<Resource> getImage ( @PathVariable String fileName,
                                               HttpServletRequest request ) throws Exception
    {
        UserInfoEntity user = userRepo.findByUiFile(fileName);
        if(user==null){
            throw new NotFoundFileException();
        }
        Path folderLocation = null;

        folderLocation = Paths.get(profile_img_path);
        String[] split = fileName.split("\\.");
        String ext = split[split.length - 1];
        String exportName = user.getUiUri() + "." + ext;

        Path targetFile = folderLocation.resolve(user.getUiFile());
        Resource r = null;
        try {
            r = new UrlResource(targetFile.toUri());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(r.getFile().getAbsolutePath());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(exportName, "UTF-8") + "\"")
                .body(r);
    }
}
