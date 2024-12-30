package com.framework.admin.controller.comm;

import com.framework.admin.controller.comm.dto.*;
import com.framework.admin.entity.AdminInfoJpa;
import com.framework.admin.service.comm.AdminManageService;
import com.framework.admin.service.comm.dto.*;
import com.framework.common.handler.CommonApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

/**
 * packageName    : com.framework.admin.controller.comm.dto
 * fileName       : AdminManageApiController
 * author         : NAMANOK
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        NAMANOK       최초 생성
 */
@RequiredArgsConstructor
@Slf4j
@RestController
public class AdminManageApiController {

    private final AdminManageService adminManageService;

    /**
     * 관리자 목록 조회
     *
     * @param model
     * @param cInDto
     * @return
     */
    @PostMapping(value = "/comm/admin-manage/admin-manage-list", produces = "application/json; charset=utf-8")
    public ResponseEntity<CommonApiResponse> adminManageList(Model model, @RequestBody AdminManageListCInDto cInDto) {

        AdminManageListCOutDto cOutDto = new AdminManageListCOutDto();

        AdminManageListSInDto sInDto = new AdminManageListSInDto();
        if(!StringUtils.isEmpty(cInDto.getSearchGubun()) && !StringUtils.isEmpty(cInDto.getSearchText())) {
            if("id".equals(cInDto.getSearchGubun())) {
                sInDto.setAdminId(cInDto.getSearchText());
            } else if("name".equals(cInDto.getSearchGubun())) {
                sInDto.setAdminName(cInDto.getSearchText());
            }
        }

        BeanUtils.copyProperties(cInDto, sInDto);
        Page<AdminInfoJpa> resultList= adminManageService.adminManageList(sInDto);

        cOutDto.setPageNo(cInDto.getPageNo());
        cOutDto.setPageSize(cInDto.getPageSize());
        cOutDto.setResultList(resultList.getContent());
        cOutDto.setTotalPageCount(resultList.getTotalPages());

        return new ResponseEntity<>(CommonApiResponse.ok(cOutDto), HttpStatus.OK);
    }

    /**
     * 관리자 상세 조회
     *
     * @param model
     * @param cInDto
     * @return
     */
    @PostMapping(value = "/comm/admin-manage/admin-manage-detail", produces = "application/json; charset=utf-8")
    public ResponseEntity<CommonApiResponse> adminManageDetail(Model model, @RequestBody AdminManageDetailCInDto cInDto) {

        AdminManageDetailCOutDto cOutDto = new AdminManageDetailCOutDto();

        AdminManageDetailSInDto sInDto = new AdminManageDetailSInDto();

        BeanUtils.copyProperties(cInDto, sInDto);
        AdminManageDetailSOutDto sOutDto= adminManageService.adminManageDetail(sInDto);
        BeanUtils.copyProperties(sOutDto, cOutDto);

        return new ResponseEntity<>(CommonApiResponse.ok(cOutDto), HttpStatus.OK);
    }

    /**
     * 관리자 등록
     *
     * @param model
     * @param cInDto
     * @return
     */
    @PostMapping(value = "/comm/admin-manage/admin-manage-save", produces = "application/json; charset=utf-8")
    public ResponseEntity<CommonApiResponse> adminManageSave(Model model, @RequestBody AdminManageSaveCInDto cInDto) {

        AdminManageSaveCOutDto cOutDto = new AdminManageSaveCOutDto();

        AdminManageSaveSInDto sInDto = new AdminManageSaveSInDto();
        BeanUtils.copyProperties(cInDto, sInDto);
        adminManageService.adminManageSave(sInDto);

        cOutDto.setResult("success");

        return new ResponseEntity<>(CommonApiResponse.ok(cOutDto), HttpStatus.OK);
    }

    /**
     * 관리자 수정
     * @param model
     * @param cInDto
     * @return
     */
    @PostMapping(value = "/comm/admin-manage/admin-manage-update", produces = "application/json; charset=utf-8")
    public ResponseEntity<CommonApiResponse> adminManageUpdate(Model model, @RequestBody AdminManageUpdateCInDto cInDto) {

        AdminManageUpdateCOutDto cOutDto = new AdminManageUpdateCOutDto();

        AdminManageUpdateSInDto sInDto = new AdminManageUpdateSInDto();
        BeanUtils.copyProperties(cInDto, sInDto);
        adminManageService.adminManageUpdate(sInDto);

        cOutDto.setResult("success");

        return new ResponseEntity<>(CommonApiResponse.ok(cOutDto), HttpStatus.OK);
    }

    /**
     * 관지자 삭제
     * @param model
     * @param cInDto
     * @return
     */
    @PostMapping(value = "/comm/admin-manage/admin-manage-delete", produces = "application/json; charset=utf-8")
    public ResponseEntity<CommonApiResponse> adminManageDelete(Model model, @RequestBody AdminManageDeleteCInDto cInDto) {

        AdminManageDeleteCOutDto cOutDto = new AdminManageDeleteCOutDto();

        AdminManageDeleteSInDto sInDto = new AdminManageDeleteSInDto();
        BeanUtils.copyProperties(cInDto, sInDto);
        adminManageService.adminManageDelete(sInDto);

        cOutDto.setResult("success");

        return new ResponseEntity<>(CommonApiResponse.ok(cOutDto), HttpStatus.OK);
    }
}
