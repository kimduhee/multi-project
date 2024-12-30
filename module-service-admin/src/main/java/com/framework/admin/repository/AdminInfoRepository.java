package com.framework.admin.repository;

import com.framework.admin.entity.AdminInfoJpa;
import com.framework.admin.service.comm.dto.AdminManageDetailSInDto;
import com.framework.admin.service.comm.dto.AdminManageDetailSOutDto;
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
public interface AdminInfoRepository extends JpaRepository<AdminInfoJpa, String>, JpaSpecificationExecutor<AdminInfoJpa> {
    Page<AdminInfoJpa> findAll(Specification<AdminInfoJpa> spec, Pageable pageable);
    AdminInfoJpa findByAdminId(String adminId);
    void deleteByAdminId(String adminId);
}
