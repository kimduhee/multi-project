package com.framework.admin.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="ADMIN_INFO")
@Entity
@DynamicUpdate
public class AdminInfoEntity {

    @Id
    @Column(name="ADMIN_NO", nullable = false)
    private int adminNo;

    @Column(name="ADMIN_ID", length = 20, nullable = false)
    private String adminId;

    @Column(name="ADMIN_NAME", length = 30, nullable = true)
    private String adminName;

    @Column(name="ADMIN_PASSWORD", length = 20, nullable = false)
    private String adminPassword;

    @Column(name = "ADMIN_LEVEL", length = 20, nullable = false)
    private String adminLevel;

    @Column(name = "USE_YN", length = 1, nullable = false)
    private String useYn;

    @Column(name = "REG_NO", nullable = false)
    private int regNo;

    @CreationTimestamp
    @Column(name="REG_DT", updatable = false, nullable = false)
    private Timestamp regDt;

    @Column(name="UPD_NO", nullable = false)
    private int updNo;

    @UpdateTimestamp
    //@LastModifiedDate
    @Column(name = "UPD_DT", nullable = false)
    private Timestamp updDt;

    @Builder
    protected AdminInfoEntity(String adminId, String adminName, String adminPassword, String adminLevel, String useYn, int regNo, int updNo) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminPassword = adminPassword;
        this.adminLevel = adminLevel;
        this.useYn = useYn;
        this.regNo = regNo;
        this.updNo = updNo;
    }

    public void updateAdminInfo(String adminName, String adminPassword, String useYn, int updNo) {
        this.adminName = adminName;
        this.adminPassword = adminPassword;
        this.useYn = useYn;
        //this.adminLevel = adminLevel;
        this.updNo = updNo;
    }
}
