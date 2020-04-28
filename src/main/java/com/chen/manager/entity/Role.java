package com.chen.manager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统表——管理员角色表
 * <p>
 * created at 2019-10-31
 *
 * @author MonoWing
 */
@Entity
@Table(name = "sys_role")
public class Role extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -2938337714508750771L;

    /**
     * 管理员角色名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 内置标志，是否为内置的角色，内置角色不能删除
     */
    private Boolean internalSign;

    /**
     * 获取管理员角色名称
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 设置管理员角色名称
     *
     * @param name 角色名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取描述
     *
     * @return
     */

    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param desc 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取内置标志
     *
     * @return
     */
    @Column(updatable = false)
    public Boolean getInternalSign() {
        return internalSign;
    }

    /**
     * 设置内置标记
     *
     * @param internalSign
     */
    public void setInternalSign(Boolean internalSign) {
        this.internalSign = internalSign;
    }


    @Override
    public String toString() {
        return "Role [name=" + name + ", description=" + description + "]";
    }
}