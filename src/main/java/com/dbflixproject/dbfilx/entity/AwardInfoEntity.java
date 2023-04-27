package com.dbflixproject.dbfilx.entity;

import com.dbflixproject.dbfilx.entity.enumfile.AwardCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="award_info")
public class AwardInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ai_seq")
    private Long aiSeq;

    @Column(name="ai_name")
    private String aiName;

    @Column(name="ai_year")
    private Integer aiYear;

    @Column(name="ai_cate")
    @Enumerated(value = EnumType.STRING)
    private AwardCategory aiCate;

    public AwardInfoEntity(String name, Integer year, AwardCategory cate){
        this.aiName = name;
        this.aiYear = year;
        this.aiCate = cate;
    }

    public void updateData(String name, Integer year, AwardCategory cate){
        if(StringUtils.hasText(name)){
            this.aiName = name;
        }
        if(year!=null && year>=1900){
            this.aiYear = year;
        }
        if(cate!=null){
            this.aiCate = cate;
        }
    }
}
