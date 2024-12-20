package com.framework.common.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/**
 * packageName    : com.framework.common.entity
 * fileName       : UserInfoToken
 * author         : NAMANOK
 * date           : 2024-12-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-20        NAMANOK       최초 생성
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="USER_INFO_TOKEN")
@Entity
public class UserInfoTokenJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID", length = 10, nullable = false)
    private Integer userId;

    @Column(name = "USER_ACCESS_TOKEN", length = 200, nullable = true)
    private String userAccessToken;

    @Column(name = "USER_REFRESH_TOKEN", length = 200, nullable = true)
    private String userRefreshToken;

    @Column(name = "REG_ID", length = 10, nullable = false)
    private int regId;

    @CreationTimestamp
    @Column(name="REG_DT", nullable = false)
    private Date regDt;

    @Column(name="UPD_ID", length = 10, nullable = false)
    private int updId;

    @UpdateTimestamp
    @Column(name = "UPD_DT", nullable = false)
    private Date updDt;

    @Builder
    protected UserInfoTokenJpa(Integer userId, String userAccessToken, String userRefreshToken, int regId, int updId) {
        this.userId = userId;
        this.userAccessToken = userAccessToken;
        this.userRefreshToken = userRefreshToken;
        this.regId = regId;
        this.updId = updId;
    }
}
