package com.chen.manager.enumbean;

/**
 * 枚举——性别
 * 
 * created at 2019-10-31
 * 
 * @author MonoWing
 *
 */
public enum Gender {

	/**
	 * 男
	 */
	MALE("1", "男"),
	/**
	 * 女
	 */
	FEMALE("2", "女");

	/**
	 * 枚举值
	 */
	private String value;

	/**
	 * 枚举描述
	 */
	private String desc;

	/**
	 * 获取枚举值
	 * 
	 * @return 枚举值
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 设置枚举值
	 * 
	 * @param value
	 *            枚举值
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 获取枚举描述
	 * 
	 * @return 枚举描述
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 设置枚举描述
	 * 
	 * @param desc
	 *            枚举描述
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	private Gender(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

}
