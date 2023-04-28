package com.dbflixproject.dbfilx.entity.movie;

import static org.springframework.util.StringUtils.hasText;

import java.time.LocalDate;

import com.dbflixproject.dbfilx.entity.CompanyInfoEntity;
import com.dbflixproject.dbfilx.entity.enumfile.MovieGenre;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String miCountry;

    @Column(name="mi_genre")
    @Enumerated(value = EnumType.STRING)
    private MovieGenre miGenre;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="mi_com_seq")
    private CompanyInfoEntity company;

    public void changeData(String name, Integer attendance, LocalDate regDt, Integer price, String country, MovieGenre genre){
        if(hasText(name)){
            this.miTitle = name;
        }
        if(attendance!=null){
            this.miAttendance = attendance;
        }
        if(regDt!=null){
            this.miRegDt = regDt;
        }
        if(price!=null){
            this.miPrice = price;
        }
        if(hasText(country)){
            this.miCountry = country;
        }
        if(genre!=null){
            this.miGenre = genre;
        }
    }
}







