package com.chen.manager.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.chen.manager.enumbean.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 系统表——管理员
 * <p>
 * created at 2019-10-31
 *
 * @author MonoWing
 */
@Entity
@Table(name = "sys_admin")
public class Admin extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1642249177713164868L;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 角色
     */
    private Role role;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别
     */
    private Gender gender;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否可用
     */
    private Boolean enabled;

    /**
     * 备注
     */
    private String remarks;

    /**
     * Token值
     */
    private String token;

    /**
     * Token创建时间
     */
    private Date tokenDate;

    /**
     * 获取用户名
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取密码
     *
     * @return
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * 设置密码
     *
     * @param passWord 密码
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * 获取角色
     *
     * @return
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler",
            "fieldHandler"})
    public Role getRole() {
        return role;
    }

    /**
     * 设置角色
     *
     * @param role 角色
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * 获取昵称
     *
     * @return
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置昵称
     *
     * @param name 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取性别
     *
     * @return
     */
    @Enumerated(EnumType.ORDINAL)
    @Column
    public Gender getGender() {
        return gender;
    }

    /**
     * 设置性别
     *
     * @param gender 性别
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * 获取头像
     *
     * @return
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像
     *
     * @param avatar 头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取电话
     *
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取邮箱
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取管理员是否可用
     *
     * @return
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置管理员是否可用
     *
     * @param enabled 是否可用
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取备注
     *
     * @return
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取Token值
     *
     * @return
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置Token值
     *
     * @param token Token值
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取Token创建时间
     *
     * @return
     */
    public Date getTokenDate() {
        return tokenDate;
    }

    /**
     * 设置Token创建时间
     *
     * @param tokenDate Token创建时间
     */
    public void setTokenDate(Date tokenDate) {
        this.tokenDate = tokenDate;
    }

    @Transient
    public String getRoleStr() {
        if (role == null) {
            return "";
        }
        return role.getName();
    }

}