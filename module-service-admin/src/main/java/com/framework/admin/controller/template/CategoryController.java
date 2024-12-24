package com.framework.admin.controller.template;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 카테고리 관리
 *
 * packageName    : com.framework.admin.controller.template
 * fileName       : CategoryController
 * author         : NAMANOK
 * date           : 2024-12-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-24        NAMANOK       최초 생성
 */
@Controller
public class CategoryController {

    @GetMapping("/template/category/category-main")
    public String categoryMain() {
        return "admin/template/category/category-main";
    }
}
