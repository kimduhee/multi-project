package com.framework.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName    : com.framework.admin.controller
 * fileName       : MainController
 * author         : NAMANOK
 * date           : 2024-12-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-23        NAMANOK       최초 생성
 */
@Controller
public class MainController {

    @GetMapping("/main")
    public String main() {
        return "admin/main";
    }
}
