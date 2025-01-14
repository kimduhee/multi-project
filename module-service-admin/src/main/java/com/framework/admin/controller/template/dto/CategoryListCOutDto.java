package com.framework.admin.controller.template.dto;

import com.framework.admin.entity.TemplateCategoryEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * packageName    : com.framework.admin.controller.template.dto
 * fileName       : CategoryListCOutDto
 * author         : NAMANOK
 * date           : 2025-01-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-01-08        NAMANOK       최초 생성
 */
@Getter
@Setter
public class CategoryListCOutDto {
    List<TemplateCategoryEntity> categoryList;
}
