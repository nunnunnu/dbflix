package com.dbflixproject.dbfilx.entity.creator;

import com.dbflixproject.dbfilx.entity.movie.MovieInfoEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="creator_movie_connection")
public class CreatorMovieConnectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmc_seq")
    private Long cmcSeq;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "cmc_ci_seq")
    private CreatorInfoEntity creator;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "cmc_mi_seq")
    private MovieInfoEntity movie;

    @Column(name = "cmc_role")
    private String cmcRole;

}



