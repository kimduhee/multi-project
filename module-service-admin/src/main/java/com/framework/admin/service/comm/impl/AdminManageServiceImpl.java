package com.framework.admin.service.comm.impl;

import com.framework.admin.entity.AdminInfoJpa;
import com.framework.admin.repository.AdminInfoRepository;
import com.framework.admin.repository.AdminSpecification;
import com.framework.admin.service.comm.AdminManageService;
import com.framework.admin.service.comm.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

/**
 * packageName    : com.framework.admin.service.comm.impl
 * fileName       : AdminServiceImpl
 * author         : NAMANOK
 * date           : 2024-12-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-26        NAMANOK       최초 생성
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class AdminManageServiceImpl implements AdminManageService {

    private final AdminInfoRepository adminInfoRepository;

    /**
     * 관리자 조회
     * @param sInDto
     * @return
     */
    @Transactional(readOnly=true, rollbackFor=Exception.class)
    @Override
    public Page<AdminInfoJpa> adminManageList(AdminManageListSInDto sInDto) {

        Specification<AdminInfoJpa> spec = (root, query, criteriaBuilder) -> null;

        if(!StringUtils.isEmpty(sInDto.getAdminId())) {
            spec = spec.and(AdminSpecification.likeAdminId(sInDto.getAdminId()));
        }
        if(!StringUtils.isEmpty(sInDto.getAdminName())) {
            spec = spec.and(AdminSpecification.likeAdminName(sInDto.getAdminName()));
        }

        Pageable pageable = PageRequest.of(sInDto.getPageNo()-1, sInDto.getPageSize(), Sort.by("updDt").descending());

        Page<AdminInfoJpa> adminList =  adminInfoRepository.findAll(spec, pageable);
        return adminList;
    }

    /**
     * 관리자 상세 조회
     * @param sInDto
     * @return
     */
    @Transactional(readOnly=true, rollbackFor=Exception.class)
    @Override
    public AdminManageDetailSOutDto adminManageDetail(AdminManageDetailSInDto sInDto) {
        AdminManageDetailSOutDto sOutDto = new AdminManageDetailSOutDto();
        AdminInfoJpa adminInfoJpa =  adminInfoRepository.findByAdminId(sInDto.getAdminId());
        BeanUtils.copyProperties(adminInfoJpa, sOutDto );
        return sOutDto;
    }

    /**
     * 관리자 등록
     * @param sInDto
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
    @Override
    public void adminManageSave(AdminManageSaveSInDto sInDto) {
        AdminInfoJpa adminInfoJpa = AdminInfoJpa.builder()
                .adminId(sInDto.getAdminId())
                .adminName(sInDto.getAdminName())
                .adminPassword(sInDto.getAdminPassword())
                .build();
        adminInfoRepository.save(adminInfoJpa);
    }

    /**
     * 관리자 수정
     * @param sInDto
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
    @Override
    public void adminManageUpdate(AdminManageUpdateSInDto sInDto) {
        AdminInfoJpa adminInfoJpa = adminInfoRepository.findByAdminId(sInDto.getAdminId());
        adminInfoJpa.updateAdminInfo(sInDto.getAdminName(), sInDto.getAdminPassword(), sInDto.getAdminId());
        //adminInfoRepository.save(adminInfoJpa);
    }

    /**
     * 관리자 삭제
     * @param sInDto
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
    @Override
    public void adminManageDelete(AdminManageDeleteSInDto sInDto) {
        adminInfoRepository.deleteByAdminId(sInDto.getAdminId());
    }
}
