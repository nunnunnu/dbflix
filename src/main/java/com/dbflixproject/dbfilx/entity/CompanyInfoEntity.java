package com.dbflixproject.dbfilx.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import static org.springframework.util.StringUtils.hasText;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="company_info")
public class CompanyInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="com_seq")
    private Long comSeq;
    @Column(name="com_business_num")
    private String comBusinessNum;
    @Column(name="com_name")
    private String comName;
    @Column(name="com_adress")
    private String comAddress;

    public CompanyInfoEntity(String businessNum, String name, String address){
        this.comBusinessNum = businessNum;
        this.comName = name;
        this.comAddress = address;
    }

    public void updateData(String businessNum, String name, String address){
        if(hasText(businessNum)){
            this.comBusinessNum = businessNum;
        }
        if(hasText(name)){
            this.comName = name;
        }
        if(hasText(address)){
            this.comAddress = address;
        }
    }
}





