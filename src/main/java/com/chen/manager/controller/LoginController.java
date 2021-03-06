package com.chen.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.manager.annotation.OperLog;
import com.chen.manager.common.OperationType;
import com.chen.manager.service.AdminService;
import com.chen.manager.viewmodel.CommonResult;

/**
 * 系统——登录
 * <p>
 * created at 2019-11-01
 *
 * @author MonoWing
 */
@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;

    /**
     * 页面跳转——登录页
     *
     * @return
     */
    @RequestMapping("/login")
    private String login() {
        return "admin/base/login";
    }

    /**
     * 管理员登录
     *
     * @param userName 用户名
     * @param passWord 密码
     * @param request
     * @return
     */
    @OperLog(message = "管理员登录", operation = OperationType.LOGIN)
    @PostMapping("/loginWeb")
    @ResponseBody
    public CommonResult login(String userName, String passWord) {
        return adminService.loginWeb(userName, passWord);
    }

    /**
     * 管理员登出
     *
     * @param token Token值
     * @return
     */
    @OperLog(message = "管理员登出", operation = OperationType.LOGOUT)
    @GetMapping("/logoutWeb")
    public String logout(String token) {
        adminService.logoutWeb(token);
        return "redirect:login";
    }

}
