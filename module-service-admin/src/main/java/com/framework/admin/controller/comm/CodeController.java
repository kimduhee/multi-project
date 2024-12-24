package com.framework.admin.controller.comm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName    : com.framework.admin.controller.comm
 * fileName       : CodeController
 * author         : NAMANOK
 * date           : 2024-12-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-24        NAMANOK       최초 생성
 */
@Controller
public class CodeController {

    @GetMapping("/comm/code/code-main")
    public String codeMain() {
        return "admin/comm/code/code-main";
    }
}
