package com.framework.admin.service.comm.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.framework.admin.service.comm.dto
 * fileName       : AdminManageUpdateSInDto
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
public class AdminManageUpdateSInDto {
    private int adminNo;
    private String adminName;
    private String adminPassword;
}
