package com.framework.admin.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName    : com.framework.admin.controller
 * fileName       : UserController
 * author         : NAMANOK
 * date           : 2024-12-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-24        NAMANOK       최초 생성
 */
@Controller
public class UserController {

    @GetMapping("/user/user/user-main")
    public String userInfo(Model model) {
        return "admin/user/user/user-main";
    }
}
