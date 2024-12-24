package com.framework.admin.controller.comm;

import com.framework.admin.controller.comm.dto.AdminmanageListCOutDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

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
@Controller
public class AdminManageController {

    @GetMapping("/comm/adminmanage/adminmanage-main")
    public String adminmanageMain(Model model) {
        return "admin/comm/adminmanage/adminmanage-main";
    }

    @PostMapping("/comm/adminmanage/adminmanage-list")
    public String adminmanageList(Model model) {
        List<AdminmanageListCOutDto> cOutDto = new ArrayList<>();
        cOutDto.add(new AdminmanageListCOutDto().builder().adminId("namanok").adminName("asdf").adminPassword("1234").adminLevel("11").build());
        cOutDto.add(new AdminmanageListCOutDto().builder().adminId("namanok").adminName("asdf").adminPassword("1234").adminLevel("11").build());
        cOutDto.add(new AdminmanageListCOutDto().builder().adminId("namanok").adminName("asdf").adminPassword("1234").adminLevel("11").build());
        cOutDto.add(new AdminmanageListCOutDto().builder().adminId("namanok").adminName("asdf").adminPassword("1234").adminLevel("11").build());
        cOutDto.add(new AdminmanageListCOutDto().builder().adminId("namanok").adminName("asdf").adminPassword("1234").adminLevel("11").build());
        cOutDto.add(new AdminmanageListCOutDto().builder().adminId("namanok").adminName("asdf").adminPassword("1234").adminLevel("11").build());
        cOutDto.add(new AdminmanageListCOutDto().builder().adminId("namanok").adminName("asdf").adminPassword("1234").adminLevel("11").build());
        model.addAttribute("adminmanageList",cOutDto);
        return "admin/comm/adminmanage/adminmanage-main::#adminmanageTbody";
    }
}
