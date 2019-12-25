package com.chen.manager.viewmodel;


/**
 * 角色分页Model
 * 
 * created at 2019-12-03
 * 
 * @author Administrator
 *
 */
public class RolePageVO {

	/**
	 * ID
	 */
	private Long id;

	/**
	 * 角色名
	 */
	private String name;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 内置标志
	 */
	private Boolean internalSign;

	/**
	 * 创建日期
	 */
	private String createDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getInternalSign() {
		return internalSign;
	}

	public void setInternalSign(Boolean internalSign) {
		this.internalSign = internalSign;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "RolePageVO [id=" + id + ", name=" + name + ", description="
				+ description + ", internalSign=" + internalSign
				+ ", createDate=" + createDate + "]";
	}

	

}
