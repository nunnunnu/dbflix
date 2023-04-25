package com.dbflixproject.dbfilx;

import com.dbflixproject.dbfilx.entity.CompanyInfoEntity;
import com.dbflixproject.dbfilx.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
