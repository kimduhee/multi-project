package com.framework.admin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name="TEMPLATE_CATEGORY")
@Entity
@DynamicUpdate
public class TemplateCategoryEntity {

    @Id
    @Column(name="TEMP_CATE_NO", nullable = false)
    private int tempCateNo;

    @Column(name="TEMP_PARENTS_CATE_ID", nullable = false)
    private int tempPatentsCateId;

    @Column(name="TEMP_CATE_NAME", length = 100, nullable = false)
    private String tempCateName;

    @Column(name="USE_YN", length = 1, nullable = false)
    private String userYn;

    @Column(name = "TEMP_CATE_ORDER", nullable = false)
    private String tempCateOrder;

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
    protected TemplateCategoryEntity(int tempCateNo, int tempPatentsCateId, String tempCateName, String userYn, int regNo, int updNo) {
        this.tempCateNo = tempCateNo;
        this.tempPatentsCateId = tempPatentsCateId;
        this.tempCateName = tempCateName;
        this.userYn = userYn;
        this.regNo = regNo;
        this.updNo = updNo;
    }

}
