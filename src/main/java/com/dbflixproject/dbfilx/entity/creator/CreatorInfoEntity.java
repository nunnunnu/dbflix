package com.dbflixproject.dbfilx.entity.creator;

import com.dbflixproject.dbfilx.dto.creator.CreatorInsertDto;
import com.dbflixproject.dbfilx.entity.enumfile.CreatorType;
import com.dbflixproject.dbfilx.entity.enumfile.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import static org.springframework.util.StringUtils.*;

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

    public CreatorInfoEntity(CreatorInsertDto data){
        this.ciName = data.getName();
        this.ciGen = data.getGen();
        this.ciRole = data.getType();
        this.ciAge = data.getAge();
        this.ciCountry = data.getCountry();
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






