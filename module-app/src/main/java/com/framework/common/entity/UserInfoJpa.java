package com.framework.common.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Getter
@Table(name="USER_INFO")
@Entity
public class UserInfoJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="USER_ID", length = 10, nullable = false)
    private Long userId;

    @Column(name="USER_EMAIL", length = 50, nullable = false)
    private String userEmail;

    @Column(name="USER_NAME", length = 30, nullable = false)
    private String userName;

    @Column(name = "USER_NICKNAME", length = 30, nullable = true)
    private String userNickname;

    @Column(name="USER_PASSWORD", length = 200, nullable = false)
    private String userPassword;

    @Column(name = "USER_ROLE", length = 20, nullable = false)
    private String userRole;

    @Column(name = "REG_ID", length = 10, nullable = false)
    private Long regId;

    @Column(name="REG_DT", nullable = false)
    private Date regDt;

    @Column(name="UPD_ID", length = 10, nullable = false)
    private Long updId;

    @Column(name = "UPD_DT", nullable = false)
    private Date updDt;
}
