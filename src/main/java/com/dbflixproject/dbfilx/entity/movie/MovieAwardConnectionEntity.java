package com.dbflixproject.dbfilx.entity.movie;

import com.dbflixproject.dbfilx.entity.AwardInfoEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="movie_award_connection")
public class MovieAwardConnectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mac_seq")
    private Long macSeq;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="mac_mi_seq")
    private MovieInfoEntity movie;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="mac_ai_seq")
    private AwardInfoEntity award;
}
