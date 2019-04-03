package com.lw.modules.meeting.entity.model;

import java.io.Serializable;

public class MealTable implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tableId;
    private String mealId;
    private String joinId;
    private String tableNum;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getJoinId() {
        return joinId;
    }

    public void setJoinId(String joinId) {
        this.joinId = joinId;
    }

    public String getTableNum() {
        return tableNum;
    }

    public void setTableNum(String tableNum) {
        this.tableNum = tableNum;
    }
}
