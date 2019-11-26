package com.chen.manager.viewmodel;

import java.io.Serializable;

/**
 * 公共返回类
 * 
 * created at 2019-10-31
 * 
 * @author MonoWing
 *
 */
public class CommonResult implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4976566673643514728L;

	public static final String MES_TEXT_SUCCESS = "成功";

	public static final String MES_TEXT_ERROR = "失败";

	/**
	 * 类型
	 */
	public enum Type {

		/** 成功 */
		SUCCESS,

		/** 失败 */
		ERROR

	}

	/**
	 * 返回类型
	 */
	private Type type;

	/**
	 * 返回数据
	 */
	private Object data;

	/**
	 * 返回信息
	 */
	private String mes;

	/**
	 * 获取返回类型
	 * 
	 * @return
	 */
	public Type getType() {
		return type;
	}

	/**
	 * 设置返回类型
	 * 
	 * @param type
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * 获取返回数据
	 * 
	 * @return
	 */
	public Object getData() {
		return data;
	}

	/**
	 * 设置返回数据
	 * 
	 * @param data
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 获取返回信息
	 * 
	 * @return
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * 设置返回信息
	 * 
	 * @param mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * 构造函数
	 */
	public CommonResult() {

	}

	/**
	 * 构造函数
	 * 
	 * @param type
	 *            返回类型
	 * @param mes
	 *            返回消息
	 */
	public CommonResult(Type type, String mes) {
		this.type = type;
		this.mes = mes;
	}

	/**
	 * 构造函数
	 * 
	 * @param data
	 *            数据
	 * @param type
	 *            返回类型
	 * @param mes
	 *            返回消息
	 */
	public CommonResult(Object data, Type type, String mes) {
		this.data = data;
		this.type = type;
		this.mes = mes;
	}

	/**
	 * 成功返回
	 * 
	 * @param data
	 *            数据
	 * @param mes
	 *            消息
	 * @return
	 */
	public CommonResult success(Object data, String mes) {
		this.type = Type.SUCCESS;
		this.data = data;
		this.mes = mes;
		return this;
	}

	/**
	 * 成功返回
	 * 
	 * @param data
	 *            数据
	 * @return
	 */
	public CommonResult success(Object data) {
		this.type = Type.SUCCESS;
		this.data = data;
		this.mes = MES_TEXT_SUCCESS;
		return this;
	}

	/**
	 * 成功返回
	 * 
	 * @return
	 */
	public CommonResult success() {
		this.type = Type.SUCCESS;
		this.mes = MES_TEXT_SUCCESS;
		return this;
	}

	/**
	 * 返回失败
	 * 
	 * @param mes
	 *            消息
	 * @return
	 */
	public CommonResult error(String mes) {
		this.type = Type.ERROR;
		this.mes = mes;
		return this;
	}

	/**
	 * 返回失败
	 * 
	 * @return
	 */
	public CommonResult error() {
		this.type = Type.ERROR;
		this.mes = MES_TEXT_ERROR;
		return this;
	}

}
