package com.chen.manager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.chen.manager.entity.Admin;
import com.chen.manager.viewmodel.CommonResult;

/**
 * Service层——系统管理员
 * <p>
 * created at 2019-10-31
 *
 * @author MonoWing
 */
public interface AdminService extends BaseService<Admin, Long> {

    /**
     * 系统管理员登录
     *
     * @param userName 用户名
     * @param passWord 密码
     * @return CommonResult 登录结果
     */
    CommonResult loginWeb(String userName, String passWord);

    /**
     * 系统管理员登出
     *
     * @param token Token
     */
    void logoutWeb(String token);

    /**
     * 通过Token获取系统管理员信息
     *
     * @param token Token
     * @return
     */
    Admin getByToken(String token);

    /**
     * 新增管理员
     *
     * @param admin 管理员信息
     * @return
     */
    Admin insertAdmin(Admin admin);

    /**
     * 编辑管理员
     *
     * @param admin 管理员信息
     * @return
     */
    Admin editAdmin(Admin admin);

    /**
     * 删除管理员
     *
     * @param adminIds 管理员的ID的list
     * @return
     */
    CommonResult deleteAdmin(List<Long> adminIds);

    /**
     * 管理员信息的分页
     *
     * @param keyword     关键字
     * @param pageRequest 分页信息
     * @return
     */
    Page<Admin> pageAdmin(String keyword, PageRequest pageRequest);

    /**
     * 我的资料修改
     *
     * @param admin 管理员信息
     */
    void editMine(Admin admin);

    /**
     * 我的密码修改
     *
     * @param orgpsd     原密码
     * @param newpsd     新密码
     * @param confirmpsd 确认密码
     * @param token      Token
     * @return
     */
    CommonResult changePassword(String orgpsd, String newpsd,
                                String confirmpsd, String token);
}
