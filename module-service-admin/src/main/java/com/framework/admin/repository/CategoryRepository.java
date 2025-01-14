package com.framework.admin.repository;

import com.framework.admin.entity.AdminInfoEntity;
import com.framework.admin.entity.TemplateCategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * packageName    : com.framework.admin.repository
 * fileName       : CategoryRepository
 * author         : NAMANOK
 * date           : 2025-01-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-01-14        NAMANOK       최초 생성
 */
public interface CategoryRepository extends JpaRepository<TemplateCategoryEntity, String> {
    List<TemplateCategoryEntity> findByUseYn(String useYn);
}
