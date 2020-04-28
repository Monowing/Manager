package com.chen.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统——基础
 * <p>
 * created at 2019-11-01
 *
 * @author MonoWing
 */
@Controller
public class BaseController {

    /**
     * 页面跳转——导航页面
     *
     * @return
     */
    @RequestMapping("/index")
    private String index() {
        return "/admin/base/index";
    }

    /**
     * 页面跳转——首页
     *
     * @return
     */
    @RequestMapping("/main")
    private String main() {
        return "/admin/base/main";
    }

}
