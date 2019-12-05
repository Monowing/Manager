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
	 * 内置标志描述
	 */
	private String internalSignStr;

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

	public String getInternalSignStr() {
		if (internalSign == null || internalSign == false) {
			internalSignStr = "否";
		} else {
			internalSignStr = "是";
		}
		return internalSignStr;
	}

	public void setInternalSignStr(String internalSignStr) {
		this.internalSignStr = internalSignStr;
	}

}
