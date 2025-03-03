package com.framework.admin.repository;

import com.framework.admin.entity.AdminInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * packageName    : com.framework.admin.repository
 * fileName       : AdminInfoRepository
 * author         : NAMANOK
 * date           : 2024-12-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-26        NAMANOK       최초 생성
 */
public interface AdminInfoRepository extends JpaRepository<AdminInfoEntity, String>, JpaSpecificationExecutor<AdminInfoEntity> {
    Page<AdminInfoEntity> findAll(Specification<AdminInfoEntity> spec, Pageable pageable);
    AdminInfoEntity findByAdminNo(int adminNo);
    void deleteByAdminNo(int adminNo);
}
