package com.chen.manager.viewmodel;

/**
 * Layui的分页数据返回格式
 * <p>
 * created at 2019-12-03
 *
 * @author Administrator
 */
public class LayuiPageMode {

    /**
     * code
     */
    private Integer code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 总数量
     */
    private Long count;

    /**
     * 分页数据
     */
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
