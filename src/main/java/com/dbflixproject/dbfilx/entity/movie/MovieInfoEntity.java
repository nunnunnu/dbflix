package com.dbflixproject.dbfilx.entity.movie;

import com.dbflixproject.dbfilx.entity.CompanyInfoEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="movie_info")
public class MovieInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mi_seq")
    private Long miSeq;

    @Column(name="mi_attendance")
    private Integer miAttendance;

    @Column(name="mi_reg_dt")
    private LocalDate miRegDt;

    @Column(name="mi_title")
    private String miTitle;

    @Column(name="mi_price")
    private Integer miPrice;

    @Column(name="mi_contry")
    private String miContry;

    @Column(name="mi_genre")
    private String miGenre;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="mi_com_seq") private CompanyInfoEntity company;
}







