package com.framework.admin.service.comm;

import com.framework.admin.entity.AdminInfoJpa;
import com.framework.admin.service.comm.dto.AdminManageListSInDto;
import com.framework.admin.service.comm.dto.AdminManageSaveSInDto;
import com.framework.admin.service.comm.dto.AdminManageUpdateSInDto;
import org.springframework.data.domain.Page;

/**
 * packageName    : com.framework.admin.service.comm
 * fileName       : AdminService
 * author         : NAMANOK
 * date           : 2024-12-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-26        NAMANOK       최초 생성
 */
public interface AdminManageService {
    public Page<AdminInfoJpa> adminManageList(AdminManageListSInDto sInDto);
    public void adminManageSave(AdminManageSaveSInDto sInDto);
    public void adminManageUpdate(AdminManageUpdateSInDto sInDto);
}
