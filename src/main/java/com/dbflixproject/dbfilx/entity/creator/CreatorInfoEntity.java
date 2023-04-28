package com.dbflixproject.dbfilx.entity.creator;

import static org.springframework.util.StringUtils.hasText;

import com.dbflixproject.dbfilx.dto.creator.CreatorInsertDto;
import com.dbflixproject.dbfilx.entity.enumfile.CreatorType;
import com.dbflixproject.dbfilx.entity.enumfile.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="creator_info")
public class CreatorInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ci_seq")
    private Long ciSeq;

    @Column(name="ci_contry")
    private String ciCountry;

    @Column(name="ci_age")
    private Integer ciAge;

    @Enumerated(value = EnumType.STRING)
    @Column(name="ci_role")
    private CreatorType ciRole;

    @Enumerated(value = EnumType.STRING)
    @Column(name="ci_gen")
    private Gender ciGen;

    @Column(name="ci_name")
    private String ciName;

    public CreatorInfoEntity(String name, Gender gen, CreatorType type, Integer age, String country){
        this.ciName = name;
        this.ciGen = gen;
        this.ciRole = type;
        this.ciAge = age;
        this.ciCountry = country;
    }

    public void updateCreatorData(String name, String country, Integer age, Gender gen){
        if(hasText(name)){
            this.ciName = name;
        }
        if(hasText(country)){
            this.ciCountry = country;
        }
        if(age!=null && age!=0){
            this.ciAge = age;
        }
        if(gen!=null){
            this.ciGen = gen;
        }
    }
}






