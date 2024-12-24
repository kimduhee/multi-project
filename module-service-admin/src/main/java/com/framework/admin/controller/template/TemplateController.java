package com.framework.admin.controller.template;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName    : com.framework.admin.controller.template
 * fileName       : templateController
 * author         : NAMANOK
 * date           : 2024-12-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-24        NAMANOK       최초 생성
 */
@Controller
public class TemplateController {

    @GetMapping("/template/template/template-main")
    public String templateMain() {
        return "admin/template/template/template-main";
    }
}
