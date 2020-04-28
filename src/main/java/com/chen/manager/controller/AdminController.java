package com.chen.manager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.manager.common.Constants;
import com.chen.manager.entity.Admin;
import com.chen.manager.enumbean.Gender;
import com.chen.manager.service.AdminService;
import com.chen.manager.service.RoleService;
import com.chen.manager.utils.BaseUtils;
import com.chen.manager.viewmodel.AdminBaseInfoView;
import com.chen.manager.viewmodel.AdminPageVO;
import com.chen.manager.viewmodel.CommonResult;
import com.chen.manager.viewmodel.LayuiPageMode;

/**
 * 系统——管理员
 * <p>
 * created at 2019-11-05
 *
 * @author MonoWing
 */
@Controller
@RequestMapping("/admin/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    /**
     * 页面跳转——管理员列表页
     *
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "admin/admin/index";
    }

    /**
     * 获取管理员分页数据
     *
     * @param page    当前页数
     * @param limit   每页显示数量
     * @param keyword 查询关键字
     * @return
     */
    @PostMapping("/page")
    @ResponseBody
    public LayuiPageMode getPageData(Integer page, Integer limit, String keyword) {
        System.out.println(page + " " + limit);
        PageRequest pageRequest = PageRequest.of(page - 1, limit,
                Direction.DESC, "id");
        Page<Admin> pageAdmin = adminService.pageAdmin(keyword, pageRequest);

        List<AdminPageVO> adminList = new ArrayList<AdminPageVO>();

        List<Admin> content = pageAdmin.getContent();
        for (Admin admin : content) {
            AdminPageVO item = new AdminPageVO();
            item.setId(admin.getId());
            item.setUserName(admin.getUserName());
            item.setNickName(admin.getNickName());
            item.setGender(admin.getGender());
            item.setAvatar(admin.getAvatar());
            item.setEmail(admin.getEmail());
            item.setEnabled(admin.getEnabled());
            item.setPhone(admin.getPhone());
            item.setRemarks(admin.getRemarks());
            item.setRoleStr(admin.getRoleStr());

            adminList.add(item);
        }

        LayuiPageMode result = new LayuiPageMode();
        result.setCode(0);
        result.setCount(pageAdmin.getTotalElements());
        result.setMsg("");
        result.setData(adminList);
        return result;
    }

    /**
     * 页面跳转——管理员新增页面
     *
     * @param model
     * @return
     */
    @GetMapping("/add")
    public String add(ModelMap model) {

        model.addAttribute("genderList", Gender.values());
        model.addAttribute("roleList", roleService.findAll());

        return "admin/admin/add";
    }

    /**
     * 新增管理员
     *
     * @param admin 管理员信息
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public CommonResult add(Admin admin) {
        System.out.println(admin.toString());
        adminService.insertAdmin(admin);
        return new CommonResult().success();
    }

    /**
     * 页面跳转——管理员编辑页面
     *
     * @param model
     * @param id    管理员ID
     * @return
     */
    @GetMapping("/edit")
    public String edit(ModelMap model, Long id) {

        model.addAttribute("genderList", Gender.values());
        model.addAttribute("roleList", roleService.findAll());
        model.addAttribute("adminMode", adminService.get(id).get());

        return "admin/admin/edit";
    }

    /**
     * 编辑管理员信息
     *
     * @param admin 管理员信息
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public CommonResult edit(Admin admin, String onUsed) {
        System.out.println(onUsed);
        System.out.println(admin.toString());

        if (Constants.RADIO_CHECKED_ON.equals(onUsed)) {
            admin.setEnabled(true);
        } else {
            admin.setEnabled(false);
        }

        adminService.editAdmin(admin);
        return new CommonResult().success();
    }

    /**
     * 删除管理员
     *
     * @param ids 管理员ID的拼接字符串，用“，”分隔
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public CommonResult delete(String ids) {
        System.out.println(ids);
        return adminService.deleteAdmin(BaseUtils.convertStringToLong(ids));
    }

    /**
     * 通过Token值获取管理员基本信息
     *
     * @param token Token值
     * @return
     */
    @GetMapping("/getAdminBaseInfoView")
    @ResponseBody
    public CommonResult getAdminBaseInfoView(String token) {
        Admin admin = adminService.getByToken(token);
        if (admin != null) {
            return new CommonResult().success(new AdminBaseInfoView(admin));
        }
        return new CommonResult().error("数据获取失败");
    }
}
