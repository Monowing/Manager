package com.chen.manager.controller;

import com.chen.manager.annotation.OperLog;
import com.chen.manager.common.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.manager.entity.Admin;
import com.chen.manager.enumbean.Gender;
import com.chen.manager.service.AdminService;
import com.chen.manager.viewmodel.CommonResult;

/**
 * 系统——我的设置
 * <p>
 * created at 2019-11-06
 *
 * @author MonoWing
 */
@Controller
@RequestMapping("/admin/mine")
public class MineController {

    @Autowired
    private AdminService adminService;

    /**
     * 页面跳转——基本资料页面
     *
     * @return
     */
    @GetMapping("/baseInfo")
    public String baseInfo(ModelMap model) {
        model.addAttribute("genderList", Gender.values());
        return "admin/mine/baseInfo";
    }

    /**
     * 页面跳转——修改密码页面
     *
     * @return
     */
    @GetMapping("/changePsd")
    public String changePsd(ModelMap model) {
        return "/admin/mine/changePsd";
    }

    /**
     * 我的——设置我的资料
     *
     * @param admin 管理员信息
     * @return
     */
    @OperLog(message = "修改我的资料", operation = OperationType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public CommonResult edit(Admin admin) {
        System.out.println(admin.toString());
        adminService.editMine(admin);
        return new CommonResult().success();
    }

    /**
     * 我的——密码修改
     *
     * @param userName   用户名
     * @param orgpsd     原始密码
     * @param newpsd     新密码
     * @param confirmpsd 确认密码
     * @param token      Token
     * @return
     */
    @OperLog(message = "修改密码", operation = OperationType.UPDATE)
    @PostMapping("/psdChange")
    @ResponseBody
    public CommonResult psdChange(String userName, String orgpsd,
                                  String newpsd, String confirmpsd, String token) {
        System.out.println(orgpsd + " " + newpsd + " " + confirmpsd + " "
                + token);
        return adminService.changePassword(orgpsd, newpsd, confirmpsd, token);
    }
}
