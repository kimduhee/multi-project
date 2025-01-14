package com.framework.admin.service.template;


import com.framework.admin.entity.TemplateCategoryEntity;

import java.util.List;

/**
 * packageName    : com.framework.admin.service.template
 * fileName       : CategoryService
 * author         : NAMANOK
 * date           : 2025-01-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-01-08        NAMANOK       최초 생성
 */
public interface CategoryService {
    public List<TemplateCategoryEntity> categoryList();
}
