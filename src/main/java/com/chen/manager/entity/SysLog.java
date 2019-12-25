package com.chen.manager.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统日志
 * 
 * created at 2019-12-24
 * 
 * @author MonoWing
 *
 */
@Entity
@Table(name = "sys_log")
public class SysLog extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4592047082729727079L;

	/**
	 * 管理员ID
	 */
	private String adminId;

	/**
	 * 消息
	 */
	private String message;

	/**
	 * 日志类型
	 */
	private String operation;

	/**
	 * 请求方法
	 */
	private String method;

	/**
	 * 请求参数
	 */
	private String params;

	/**
	 * IP
	 */
	private String ip;

	/**
	 * 总耗时（毫秒）
	 */
	private Long totalTime;

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Long totalTime) {
		this.totalTime = totalTime;
	}

	@Override
	public String toString() {
		return "SysLog [adminId=" + adminId + ", message=" + message
				+ ", operation=" + operation + ", method=" + method
				+ ", params=" + params + ", ip=" + ip + ", totalTime="
				+ totalTime + "]";
	}

}
