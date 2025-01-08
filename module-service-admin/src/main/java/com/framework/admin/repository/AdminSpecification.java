package com.framework.admin.repository;

import com.framework.admin.entity.AdminInfoEntity;
import org.springframework.data.jpa.domain.Specification;

/**
 * packageName    : com.framework.admin.repository
 * fileName       : AdminSpecification
 * author         : NAMANOK
 * date           : 2024-12-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-26        NAMANOK       최초 생성
 */
public class AdminSpecification {

    public static Specification<AdminInfoEntity> notAdminId() {
        //TODO super admin 계정 설정관리 필요할듯
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.notEqual(root.get("adminId"), "admin");
    }

    public static Specification<AdminInfoEntity> likeAdminId(String adminId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("adminId"), "%" + adminId + "%");
    }

    public static Specification<AdminInfoEntity> likeAdminName(String adminName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("adminName"), "%" + adminName + "%");
    }
}
