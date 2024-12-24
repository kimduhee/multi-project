package com.framework.admin.controller.comm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName    : com.framework.admin.controller.comm
 * fileName       : TermsController
 * author         : NAMANOK
 * date           : 2024-12-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-24        NAMANOK       최초 생성
 */
@Controller
public class TermsController {

    @GetMapping("/comm/terms/terms-main")
    public String termsMain() {
        return "admin/comm/terms/terms-main";
    }
}
