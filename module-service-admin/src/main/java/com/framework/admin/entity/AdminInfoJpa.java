package com.framework.admin.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="ADMIN_INFO")
@Entity
@DynamicUpdate
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
    private Timestamp regDt;

    @Column(name="UPD_ID", length = 20, nullable = false)
    private String updId;

    @UpdateTimestamp
    //@LastModifiedDate
    @Column(name = "UPD_DT", nullable = false)
    private Timestamp updDt;

    @Builder
    protected AdminInfoJpa(String adminId, String adminName, String adminPassword, String adminLevel, String regId, String updId) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminPassword = adminPassword;
        this.adminLevel = adminLevel;
        this.regId = regId;
        this.updId = updId;
    }

    public void updateAdminInfo(String adminName, String adminPassword, String updId) {
        this.adminName = adminName;
        this.adminPassword = adminPassword;
        //this.adminLevel = adminLevel;
        this.updId = updId;
    }
}
