package com.framework.admin.service.comm.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.framework.admin.service.comm.dto
 * fileName       : AdminManageDetailSOutDto
 * author         : NAMANOK
 * date           : 2024-12-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-30        NAMANOK       최초 생성
 */
@Getter
@Setter
public class AdminManageDetailSOutDto {
    private String adminId;
    private String adminName;
    private String adminPassword;
    private String adminLevel;
}
