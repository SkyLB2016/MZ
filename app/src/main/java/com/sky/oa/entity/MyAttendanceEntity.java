package com.sky.oa.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2022/5/28 5:50 下午
 * @Version: 1.0
 */
public class MyAttendanceEntity {
    private long date;//1653235200000,

    private String days;//"22",每月几号
    private boolean today;//false,是否是今天

    private String workTime;//"无",工作时长

    private boolean over;//false,是否加班
    private boolean leaves;//false,是否休假
    private boolean onBusiness;//false,是否调休
    private boolean outdoor;//false是否外勤
    private boolean queka;//false缺卡

//    private String yearMonth;// 年月 格式："2022年5月"（直接显示即可）
//    private String week;//年内第几周
//    private String month;// 年内第几月
//    private String totalWorkTime;//	总计加班时长 （直接显示即可）


    public MyAttendanceEntity() {
    }

    public MyAttendanceEntity(String days, boolean today) {
        this.days = days;
        this.today = today;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public boolean isToday() {
        return today;
    }

    public void setToday(boolean today) {
        this.today = today;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public boolean isLeaves() {
        return leaves;
    }

    public void setLeaves(boolean leaves) {
        this.leaves = leaves;
    }

    public boolean isOnBusiness() {
        return onBusiness;
    }

    public void setOnBusiness(boolean onBusiness) {
        this.onBusiness = onBusiness;
    }

    public boolean isOutdoor() {
        return outdoor;
    }

    public void setOutdoor(boolean outdoor) {
        this.outdoor = outdoor;
    }

    public boolean isQueka() {
        return queka;
    }

    public void setQueka(boolean queka) {
        this.queka = queka;
    }

    public List<Integer> getDayStatus() {
        List<Integer> status = new ArrayList<>();
        if (!over && !leaves && !onBusiness && !outdoor) {
            status.add(0);
            return status;
        }
        if (over) {//false,是否加班
            status.add(1);
        }
        if (onBusiness) {//false,是否出差
            status.add(2);
        }
        if (leaves) {//false,是否休假
            status.add(3);
        }
        if (outdoor) {//false是否外勤
            status.add(4);
        }
        return status;
    }

}
