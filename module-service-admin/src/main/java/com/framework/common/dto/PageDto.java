package com.framework.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.framework.common.dto
 * fileName       : PageDto
 * author         : NAMANOK
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        NAMANOK       최초 생성
 */
@Getter
@Setter
public class PageDto {
    private int pageNo;
    private int pageSize;
}
