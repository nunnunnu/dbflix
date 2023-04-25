package com.dbflixproject.dbfilx.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String aiCate;
}
