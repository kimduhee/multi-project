package com.framework.admin.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="USER_INFO")
@Entity
public class UserInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="USER_NO", length = 10, nullable = false)
    private Integer userNo;

    @Column(name="USER_EMAIL", unique = true, length = 50, nullable = false)
    private String userEmail;

    @Column(name="USER_NAME", length = 30, nullable = true)
    private String userName;

    @Column(name = "USER_NICKNAME", length = 30, nullable = true)
    private String userNickname;

    @Column(name="USER_PASSWORD", length = 200, nullable = false)
    private String userPassword;

    @Column(name = "USER_ROLE", length = 20, nullable = false)
    private String userRole;

    @Column(name = "REG_NO", length = 10, nullable = false)
    private int regNo;

    @CreationTimestamp
    @Column(name="REG_DT", updatable = false, nullable = false)
    private Date regDt;

    @Column(name="UPD_NO", length = 10, nullable = false)
    private int updNo;

    @UpdateTimestamp
    @Column(name = "UPD_DT", nullable = false)
    private Date updDt;

    @Builder
    protected UserInfoEntity(String userEmail, String userName, String userNickname, String userPassword, String userRole, int regNo, int updNo) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userNickname = userNickname;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.regNo = regNo;
        this.updNo = updNo;
    }
}
