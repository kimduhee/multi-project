package com.framework.admin.controller.template;

import com.framework.admin.controller.template.dto.CategoryListCInDto;
import com.framework.admin.controller.template.dto.CategoryListCOutDto;
import com.framework.common.handler.CommonApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CategoryApiController {

    @PostMapping(value = "/template/category/category-list", produces = "application/json; charset=utf-8")
    public ResponseEntity<CommonApiResponse> categoryList(@RequestBody CategoryListCInDto cInDto) {

        CategoryListCOutDto cOutDto = new CategoryListCOutDto();
        return new ResponseEntity<>(CommonApiResponse.ok(cOutDto), HttpStatus.OK);
    }
}
