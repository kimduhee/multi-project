package com.framework.admin.service.comm;

import com.framework.admin.entity.AdminInfoEntity;
import com.framework.admin.service.comm.dto.*;
import org.springframework.data.domain.Page;

/**
 * packageName    : com.framework.admin.service.comm
 * fileName       : AdminManageService
 * author         : NAMANOK
 * date           : 2024-12-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-26        NAMANOK       최초 생성
 */
public interface AdminManageService {
    public Page<AdminInfoEntity> adminManageList(AdminManageListSInDto sInDto);
    public AdminManageDetailSOutDto adminManageDetail(AdminManageDetailSInDto sInDto);
    public void adminManageSave(AdminManageSaveSInDto sInDto);
    public void adminManageUpdate(AdminManageUpdateSInDto sInDto);
    public void adminManageDelete(AdminManageDeleteSInDto sInDto);
}
