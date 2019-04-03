package com.lw.modules.meeting.entity.model;

import java.io.Serializable;

/**
 * @program: meeting
 * @Date: 2019/3/15 14:58
 * @Author: wangy
 * @Description:
 */
public class DataModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private Object list;
    private Object param;

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }
}
