package com.lw.modules.meeting.entity.model;

import java.io.Serializable;

public class JoinDepartment implements Serializable {
    private static final long serialVersionUID = 1L;
    private String departmentName;    //单位名称
    private String departmentCode;    //单位编码

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
}
