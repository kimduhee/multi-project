package com.framework.web.common.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommController {

    /**
     * 400 에러 화면처리
     * @param model
     * @return
     */
    @RequestMapping(value="/error/400", method={RequestMethod.GET, RequestMethod.POST})
    public String error400(Model model) {
        return "comm/400";
    }

    /**
     * 401 에러 화면처리
     * @param model
     * @return
     */
    @RequestMapping(value="/error/401", method={RequestMethod.GET, RequestMethod.POST})
    public String error401(Model model) {
        return "comm/401";
    }

    /**
     * 403 에러 화면처리
     * @param model
     * @return
     */
    @RequestMapping(value="/error/403", method={RequestMethod.GET, RequestMethod.POST})
    public String error403(Model model) {
        return "comm/403";
    }

    /**
     * 404 에러 화면처리
     * @param model
     * @return
     */
    @RequestMapping(value="/error/404", method={RequestMethod.GET, RequestMethod.POST})
    public String error404(Model model) {
        return "comm/404";
    }

    /**
     * 500 에러 화면처리
     * @param model
     * @return
     */
    @RequestMapping(value="/error/500", method={RequestMethod.GET, RequestMethod.POST})
    public String error500(Model model) {
        return "comm/500";
    }
}
