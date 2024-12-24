package com.framework.admin.controller.comm.dto;

import lombok.*;

/**
 * packageName    : com.framework.admin.controller.comm.dto
 * fileName       : AdminmanageListCOutDto
 * author         : NAMANOK
 * date           : 2024-12-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-24        NAMANOK       최초 생성
 */
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AdminmanageListCOutDto {
    private String adminId;
    private String adminName;
    private String adminPassword;
    private String adminLevel;
}
