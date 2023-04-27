package com.dbflixproject.dbfilx.entity.user;

import com.dbflixproject.dbfilx.entity.ReviewInfoEntity;
import com.dbflixproject.dbfilx.entity.enumfile.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_info")
//@DynamicInsert
public class UserInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ui_seq")
    private Long uiSeq;

    @Column(name="ui_id")
    private String uiId;

    @Column(name="ui_pwd")
    private String uiPwd;

    @Column(name="ui_name")
    private String uiName;

    @Column(name="ui_email")
    private String uiEmail;

    @Column(name="ui_file")
    private String uiFile;

    @Column(name="ui_uri")
    private String uiUri;

    @Column(name="ui_birth")
    private LocalDate uiBirth;

    @Enumerated(value = EnumType.STRING)
    @Column(name="ui_gen")
    private Gender uiGen;

    @Column(name="ui_status")
    private Boolean uiStatus;

    @OneToMany(mappedBy = "user")
    private List<ReviewInfoEntity> review = new ArrayList<>();

    @PrePersist
    public void prePersist(){
        this.uiGen = this.uiGen==null?Gender.선택안함:this.uiGen;
        this.uiStatus = this.uiStatus==null?true:this.uiStatus;
    }

    public void joinData(String id, String pwd, String name, String email, LocalDate birth, Gender gen){
        this.uiId = id;
        this.uiPwd = pwd;
        this.uiName = name;
        this.uiEmail = email;
        this.uiBirth = birth;
        this.uiGen = gen;
    }

    public void fileSetting(String fileName, String originFileName){
        this.uiFile = fileName;
        this.uiUri = originFileName;
    }

    public void changeUserInfo(String pwd, String email, String name, Gender gen){
        if(hasText(pwd)){
            this.uiPwd = pwd;
        }
        if(hasText(email)){
            this.uiEmail = email;
        }
        if(hasText(name)){
            this.uiName = name;
        }
        if(gen!=null){
            this.uiGen = gen;
        }
    }

    public void dropUser(){
        this.uiStatus = false;
    }


}









