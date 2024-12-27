package com.framework.admin.controller.comm.dto;

import com.framework.common.dto.PageDto;
import lombok.*;

/**
 * packageName    : com.framework.admin.controller.comm.dto
 * fileName       : AdminManageListCInDto
 * author         : NAMANOK
 * date           : 2024-12-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-24        NAMANOK       최초 생성
 */
@Getter
public class AdminManageListCInDto extends PageDto {
    private String searchGubun;
    private String searchText;
}
