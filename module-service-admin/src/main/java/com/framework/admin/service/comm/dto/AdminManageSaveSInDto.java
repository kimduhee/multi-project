package com.framework.admin.service.comm.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.framework.admin.service.comm.dto
 * fileName       : GetAdminManageSaveSInDto
 * author         : NAMANOK
 * date           : 2024-12-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-26        NAMANOK       최초 생성
 */
@Getter
@Setter
public class AdminManageSaveSInDto {
    private String adminId;
    private String adminName;
    private String adminPassword;
}
