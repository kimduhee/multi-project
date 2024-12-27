package com.framework.admin.controller.comm.dto;

import com.framework.admin.entity.AdminInfoJpa;
import com.framework.common.dto.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * packageName    : com.framework.admin.controller.comm.dto
 * fileName       : AdminManageListCOutDto
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
public class AdminManageListCOutDto {
    private List<AdminInfoJpa> resultList;
    private int pageNo;
    private int pageSize;
    private int totalPageCount;
}
