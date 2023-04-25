package com.dbflixproject.dbfilx.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestApiController {
    @GetMapping("/server")
    public String test(){
        return "연결됨";
    }
}
