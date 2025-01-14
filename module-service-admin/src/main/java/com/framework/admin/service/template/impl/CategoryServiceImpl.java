package com.framework.admin.service.template.impl;

import com.framework.admin.entity.AdminInfoEntity;
import com.framework.admin.entity.TemplateCategoryEntity;
import com.framework.admin.repository.AdminSpecification;
import com.framework.admin.repository.CategoryRepository;
import com.framework.admin.service.comm.dto.*;
import com.framework.admin.service.template.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.List;

/**
 * packageName    : com.framework.admin.service.template.impl
 * fileName       : CategoryServiceImpl
 * author         : NAMANOK
 * date           : 2025-01-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-01-08        NAMANOK       최초 생성
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * 카테고리 조회
     * @return
     */
    @Transactional(readOnly=true, rollbackFor=Exception.class)
    @Override
    public List<TemplateCategoryEntity> categoryList() {
        List<TemplateCategoryEntity> categoryList =  categoryRepository.findByUseYn("Y");
        return categoryList;
    }

}
