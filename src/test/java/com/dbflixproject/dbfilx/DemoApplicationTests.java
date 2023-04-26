package com.dbflixproject.dbfilx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dbflixproject.dbfilx.repository.AwardInfoRepository;
import com.dbflixproject.dbfilx.repository.CompanyInfoRepository;
import com.dbflixproject.dbfilx.repository.CreatorAwardConnectionRepository;
import com.dbflixproject.dbfilx.repository.CreatorInfoRepository;
import com.dbflixproject.dbfilx.repository.CreatorMovieConnectionRepository;
import com.dbflixproject.dbfilx.repository.MovieAwardConnectionRepository;
import com.dbflixproject.dbfilx.repository.MovieInfoRepository;
import com.dbflixproject.dbfilx.repository.ReviewInfoRepository;
import com.dbflixproject.dbfilx.repository.UserInfoRepository;

@SpringBootTest
class DemoApplicationTests {
	@Autowired AwardInfoRepository aiRepo;
	@Autowired CompanyInfoRepository comRepo;
	@Autowired CreatorAwardConnectionRepository cacRepo;
	@Autowired CreatorInfoRepository ciRepo;
	@Autowired CreatorMovieConnectionRepository cmcRepo;
	@Autowired MovieAwardConnectionRepository macRepo;
	@Autowired MovieInfoRepository miRepo;
	@Autowired ReviewInfoRepository riRepo;
	@Autowired UserInfoRepository uiRepo;

	@Test
	void contextLoads() {

	}
	@Test
	void 엔티티테스트(){
		aiRepo.findAll();

		comRepo.findAll();

		cacRepo.findAll();
		ciRepo.findAll();
		cmcRepo.findAll();
		macRepo.findAll();

		miRepo.findAll();
		riRepo.findAll();
		uiRepo.findAll();
	}

}
