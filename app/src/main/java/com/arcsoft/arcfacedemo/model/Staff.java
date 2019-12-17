package com.arcsoft.arcfacedemo.model;

import java.util.Date;

public class Staff {
    public static final class StaffTable{
        public static final String NAME = "staffs";
        public static final class Cols{
            public static final String ID = "id";
            public static final String NUMBER = "number";
            public static final String NAME = "name";
            public static final String STATE = "state";
            public static final String TIME = "time";
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    //虹软的代码生成的id
    private String id;
    //工号
    private String number;
    //姓名
    private String name;
    //状态，用于记录上班还是下班打卡
    private String state;
    //打卡时间
    private String time;
    public Staff(String id){
        this.id = id;
        this.time = new Date().toString();
    }
}
