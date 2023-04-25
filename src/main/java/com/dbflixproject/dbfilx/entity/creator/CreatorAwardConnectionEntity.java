package com.dbflixproject.dbfilx.entity.creator;

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
@Table(name="creator_award_connect")
public class CreatorAwardConnectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cac_seq")
    private Long cacSeq;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="cac_ci_seq")
    private CreatorInfoEntity creator;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="cac_ai_seq")
    private AwardInfoEntity award;
}





