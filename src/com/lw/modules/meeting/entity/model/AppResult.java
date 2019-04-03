package com.lw.modules.meeting.entity.model;

import java.io.Serializable;

/**
 * @program: meeting
 * @Date: 2019/3/13 15:57
 * @Author: wangy
 * @Description: app接口统一封装的返回数据
 */
public class AppResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private String status;      //接口响应的状态 200为成功
    private String msg;         //状态对应的信息
    private Object data;        //返回的数据

    public AppResult() {

    }

    public AppResult(Object data) {
        this.status = "200";
        this.msg = "OK";
        this.data = data;
    }

    public AppResult(String status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    //构建其他状态的AppResult对象
    public static AppResult build(String status, String msg, Object data) {
        return new AppResult(status, msg, data);
    }

    public static AppResult ok(Object data) {
        return new AppResult(data);
    }

    public static AppResult ok() {
        return new AppResult(null);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
