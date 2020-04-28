package com.chen.manager.viewmodel;

import java.io.Serializable;

/**
 * 公共返回类
 * <p>
 * created at 2019-10-31
 *
 * @author MonoWing
 */
public class CommonResult implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4976566673643514728L;

    public static final String MESSAGE_SUCCESS = "成功";

    public static final String MESSAGE_ERROR = "失败";

    public static final int CODE_SUCCESS = 0;

    public static final int CODE_ERROR = 1;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

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
     * 获取返回状态码0：成功；1：失败；
     *
     * @return
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 设置返回状态码 0：成功；1：失败；
     *
     * @param code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 获取返回信息
     *
     * @return
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置返回信息
     *
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 构造函数
     */
    public CommonResult() {

    }

    /**
     * 成功返回
     *
     * @param data 数据
     * @param msg  消息
     * @return
     */
    public CommonResult success(Object data, String msg) {
        this.code = CODE_SUCCESS;
        this.data = data;
        this.msg = msg;
        return this;
    }

    /**
     * 成功返回
     *
     * @param data 数据
     * @return
     */
    public CommonResult success(Object data) {
        this.code = CODE_SUCCESS;
        this.msg = MESSAGE_SUCCESS;
        this.data = data;
        return this;
    }

    /**
     * 成功返回
     *
     * @return
     */
    public CommonResult success() {
        this.code = CODE_SUCCESS;
        this.msg = MESSAGE_SUCCESS;
        return this;
    }

    /**
     * 返回失败
     *
     * @param msg 消息
     * @return
     */
    public CommonResult error(String msg) {
        this.code = CODE_ERROR;
        this.msg = msg;
        return this;
    }

    /**
     * 返回失败
     *
     * @return
     */
    public CommonResult error() {
        this.code = CODE_ERROR;
        this.msg = MESSAGE_ERROR;
        return this;
    }

    @Override
    public String toString() {
        return "CommonResult [data=" + data + ", code=" + code + ", msg=" + msg
                + "]";
    }


}
