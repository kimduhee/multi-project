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
@Table(name="ADMIN_INFO")
@Entity
public class AdminInfoJpa {

    @Id
    @Column(name="ADMIN_ID", length = 20, nullable = false)
    private String adminId;

    @Column(name="ADMIN_NAME", length = 30, nullable = true)
    private String adminName;

    @Column(name="ADMIN_PASSWORD", length = 20, nullable = false)
    private String adminPassword;

    @Column(name = "ADMIN_LEVEL", length = 20, nullable = false)
    private String adminLevel;

    @Column(name = "REG_ID", length = 20, nullable = false)
    private String regId;

    @CreationTimestamp
    @Column(name="REG_DT", updatable = false, nullable = false)
    private Date regDt;

    @Column(name="UPD_ID", length = 20, nullable = false)
    private String updId;

    @UpdateTimestamp
    @Column(name = "UPD_DT", nullable = false)
    private Date updDt;

    @Builder
    protected AdminInfoJpa(String adminId, String adminName, String adminPassword, String adminLevel, String regId, String updId) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminPassword = adminPassword;
        this.adminLevel = adminLevel;
        this.regId = regId;
        this.updId = updId;
    }
}
