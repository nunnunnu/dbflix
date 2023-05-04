package com.dbflixproject.dbfilx.entity;

import com.dbflixproject.dbfilx.dto.review.ReviewInsertDto;
import com.dbflixproject.dbfilx.entity.movie.MovieInfoEntity;
import com.dbflixproject.dbfilx.entity.user.UserInfoEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="review_info")
public class ReviewInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ri_seq")
    private Long riSeq;

    @Column(name="ri_created", updatable = false)
    private LocalDateTime riCreated;

    @Column(name="ri_coment")
    private String riComment;

    @Column(name="ri_rate")
    private Integer riRate;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ri_ui_seq")
    private UserInfoEntity user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ri_mi_seq")
    private MovieInfoEntity movie;

    public ReviewInfoEntity(String comment, Integer rate, UserInfoEntity user, MovieInfoEntity movie) {
        this.riCreated = LocalDateTime.now();
        this.riComment = comment;
        this.riRate = rate;
        this.user = user;
        this.movie = movie;
    }
}





