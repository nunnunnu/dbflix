package com.dbflixproject.dbfilx.api;

import com.dbflixproject.dbfilx.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class FileApiController {

    private final FileService fileService;

    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> getProfile(
            @PathVariable String fileName, HttpServletRequest request ) throws Exception {
        return fileService.getImage(fileName, request);
    }
}
