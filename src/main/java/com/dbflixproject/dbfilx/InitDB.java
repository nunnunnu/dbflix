package com.dbflixproject.dbfilx;

import com.dbflixproject.dbfilx.entity.AwardInfoEntity;
import com.dbflixproject.dbfilx.entity.CompanyInfoEntity;
import com.dbflixproject.dbfilx.entity.ReviewInfoEntity;
import com.dbflixproject.dbfilx.entity.creator.CreatorAwardConnectionEntity;
import com.dbflixproject.dbfilx.entity.creator.CreatorInfoEntity;
import com.dbflixproject.dbfilx.entity.creator.CreatorMovieConnectionEntity;
import com.dbflixproject.dbfilx.entity.enumfile.*;
import com.dbflixproject.dbfilx.entity.movie.MovieAwardConnectionEntity;
import com.dbflixproject.dbfilx.entity.movie.MovieInfoEntity;
import com.dbflixproject.dbfilx.entity.user.UserInfoEntity;
import com.dbflixproject.dbfilx.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;

//    @PostConstruct
//    public void init(){
//        initService.userInit(50);
//        initService.movieInit(50);
//        initService.reviewInit(10);
//        initService.creatorInit(100);
//        initService.creatorMovie(20);
//        initService.awardInit(15);
//        initService.awardConnect(10);
//    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    public static class InitService{
        private final UserInfoRepository userRepo;
        private final CompanyInfoRepository companyRepo;
        private final MovieInfoRepository movieRepo;
        private final ReviewInfoRepository reviewRepo;
        private final CreatorInfoRepository creatorRepo;
        private final CreatorMovieConnectionRepository cMovieRepo;
        private final AwardInfoRepository awardRepo;
        private final CreatorAwardConnectionRepository cAwardRepo;
        private final MovieAwardConnectionRepository mAwardRepo;

        public void userInit(int count){
            List<UserInfoEntity> users = new ArrayList<>();
            for(int i=1;i<=count;i++){
                UserInfoEntity user = new UserInfoEntity();
                user.joinData("user00"+i,"123456","사용자"+i, "user00"+i+"@email.com", LocalDate.now(), Gender.선택안함);
                users.add(user);
            }
            userRepo.saveAll(users);
        }
        public void movieInit(int count){
            List<MovieInfoEntity> movies = new ArrayList<>();
            for(int i=1;i<=count;i++){
                int attendance = (int)(Math.random()*20000000)+1;
                int price = (int)(Math.random()*1000000000)+1;
                MovieGenre genre = MovieGenre.values()[new Random().nextInt(MovieGenre.values().length)];
                CompanyInfoEntity company = companyRepo.findAll().get(0);
                movies.add(new MovieInfoEntity(null, attendance, LocalDate.now(), "영화이름"+i, price, "한국", genre, company));
            }
            movieRepo.saveAll(movies);
        }

        public void reviewInit(int count){
            List<ReviewInfoEntity> review = new ArrayList<>();
            List<UserInfoEntity> user = userRepo.findAll();
            for(UserInfoEntity u : user){
                List<MovieInfoEntity> movies = movieRepo.findAll();
                int num = (int)(Math.random()*count);
                for(int i=0;i<num;i++){
                    int rate = (int)(Math.random()*10)+1;
                    int idx = (int)(Math.random()*movies.size());
                    review.add(new ReviewInfoEntity(null, LocalDateTime.now(), "리뷰내용"+i, rate, u, movies.get(idx)));
                    movies.remove(idx);
                }
            }
            reviewRepo.saveAll(review);
        }

        public void creatorInit(int count){
            List<CreatorInfoEntity> creator = new ArrayList<>();
            for(int i=1;i<=count;i++){
                int age = (int)(Math.random()*40)+20;
                CreatorType type = CreatorType.values()[new Random().nextInt(CreatorType.values().length)];
                Gender gender = Gender.values()[new Random().nextInt(Gender.values().length)];
                creator.add(new CreatorInfoEntity(null, "한국", age, type, gender, "이름"+i));
            }
            creatorRepo.saveAll(creator);
        }

        public void creatorMovie(int count){
            List<CreatorMovieConnectionEntity> connect = new ArrayList<>();
            List<CreatorInfoEntity> creator = creatorRepo.findAll();
            for(CreatorInfoEntity c : creator){
                List<MovieInfoEntity> movies = movieRepo.findAll();
                int num = (int)(Math.random()*count);
                for(int i=0;i<num;i++){
                    int idx = (int)(Math.random()*movies.size());
                    MovieRole role = MovieRole.values()[new Random().nextInt(MovieRole.values().length)];
                    connect.add(new CreatorMovieConnectionEntity(null, c, movies.get(idx), role));
                    movies.remove(idx);
                }
            }
            cMovieRepo.saveAll(connect);
        }

        public void awardInit(int count){
            List<AwardInfoEntity> award = new ArrayList<>();
            for(int i=1;i<=count;i++){
                AwardCategory cate = AwardCategory.values()[new Random().nextInt(AwardCategory.values().length)];
                award.add(new AwardInfoEntity(null, "상이름"+i, 2023, cate));
            }
            awardRepo.saveAll(award);
        }

        public void awardConnect(int count){
            List<CreatorAwardConnectionEntity> creatorAward = new ArrayList<>();
            List<MovieAwardConnectionEntity> movieConnect = new ArrayList<>();

            List<AwardInfoEntity> actAward = awardRepo.findByAiCate(AwardCategory.배우);
            List<AwardInfoEntity> movieAward = awardRepo.findByAiCate(AwardCategory.영화);
            List<AwardInfoEntity> direcAward = awardRepo.findByAiCate(AwardCategory.감독);

            for(AwardInfoEntity a : actAward){
                List<CreatorInfoEntity> act = creatorRepo.findByCiRole(CreatorType.배우);
                int num = (int)(Math.random()*count);
                for(int i=0;i<=num;i++){
                    int idx = (int)(Math.random()*act.size());

                    creatorAward.add(new CreatorAwardConnectionEntity(null, act.get(idx), a));
                    act.remove(idx);
                }
            }
            for(AwardInfoEntity a : direcAward){
                List<CreatorInfoEntity> direc = creatorRepo.findByCiRole(CreatorType.감독);
                int num = (int)(Math.random()*count);
                for(int i=0;i<=num;i++){
                    int idx = (int)(Math.random()*direc.size());

                    creatorAward.add(new CreatorAwardConnectionEntity(null, direc.get(idx), a));
                    direc.remove(idx);
                }
            }
            for(AwardInfoEntity a : movieAward){
                List<MovieInfoEntity> movies = movieRepo.findAll();
                int num = (int)(Math.random()*count);
                for(int i=0;i<num;i++){
                    int idx = (int)(Math.random()*movies.size());
                    MovieRole role = MovieRole.values()[new Random().nextInt(MovieRole.values().length)];
                    movieConnect.add(new MovieAwardConnectionEntity(null, movies.get(idx), a));
                    movies.remove(idx);
                }
            }
            cAwardRepo.saveAll(creatorAward);
            mAwardRepo.saveAll(movieConnect);
        }
    }
}
