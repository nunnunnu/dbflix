package com.dbflixproject.dbfilx;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import com.dbflixproject.dbfilx.entity.CompanyInfoEntity;
import com.dbflixproject.dbfilx.entity.enumfile.MovieGenre;
import com.dbflixproject.dbfilx.entity.movie.MovieInfoEntity;
import com.dbflixproject.dbfilx.repository.CompanyInfoRepository;
import com.dbflixproject.dbfilx.repository.MovieInfoRepository;

import org.assertj.core.api.Fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
@Transactional
public class MovieTest {
    @Autowired
    MovieInfoRepository movieRepo;
    @Autowired
    CompanyInfoRepository companyRepo;

    private MovieInfoEntity movie;
    private CompanyInfoEntity company;

    @BeforeEach
    public void beforeEach(){
        company = new CompanyInfoEntity("333-33-33333", "제작사", "주소");
        companyRepo.save(company);
        movie = new MovieInfoEntity(null, 10000, LocalDate.now(), "테스트영화", 1000000, "한국", MovieGenre.SF, company);
        movieRepo.save(movie);
    }

    @Test
    public void 영화등록(){
        MovieInfoEntity movie = new MovieInfoEntity(null, 10000, LocalDate.now(), "테스트영화2", 10000, "한국", MovieGenre.공포, company);
        movieRepo.save(movie);

        MovieInfoEntity findMovie = movieRepo.findById(movie.getMiSeq()).orElseThrow();

        assertThat(findMovie).isNotNull();
        assertThat(findMovie.getMiSeq()).isEqualTo(movie.getMiSeq());
    }
    @Test
    public void 영화수정(){
        String originName = movie.getMiTitle();
        movie.changeData("변경이름", null, null, null, null, null);
        movieRepo.save(movie);

        assertThat(movie.getMiTitle()).isNotEqualTo(originName);
    }
    @Test
    public void 영화조회(){
        MovieInfoEntity findMovie = movieRepo.findById(movie.getMiSeq()).orElseThrow();

        assertThat(findMovie).isNotNull();
    }

    @Test
    public void 영화삭제(){
        Long seq = movie.getMiSeq();
        movieRepo.delete(movie);

        MovieInfoEntity findMovie = movieRepo.findById(seq).orElse(null);

        if(findMovie!=null){
            fail();
        }
    }

}
