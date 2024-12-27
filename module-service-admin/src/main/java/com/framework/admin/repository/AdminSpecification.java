package com.framework.admin.repository;

import com.framework.admin.entity.AdminInfoJpa;
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

    public static Specification<AdminInfoJpa> likeAdminId(String adminId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("adminId"), "%" + adminId + "%");
    }

    public static Specification<AdminInfoJpa> likeAdminName(String adminName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("adminName"), "%" + adminName + "%");
    }
}
