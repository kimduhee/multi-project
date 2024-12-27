package com.framework.admin.controller.comm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 관리자 관리
 * ㄴ
 * packageName    : com.framework.admin.controller.comm
 * fileName       : AdminManageController
 * author         : NAMANOK
 * date           : 2024-12-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-24        NAMANOK       최초 생성
 */
@RequiredArgsConstructor
@Slf4j
@Controller
public class AdminManageController {

    @GetMapping("/comm/admin-manage/admin-manage-main")
    public String adminManageMain(Model model) {
        return "admin/comm/admin-manage/admin-manage-main";
    }
}
