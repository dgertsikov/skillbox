package com.skillbox.redisdemo;

import java.io.Serializable;
import java.util.Date;

public class UserOnline implements Serializable {

    public String name;
    public Date date;
    public double time;

    public UserOnline(String name, Date date, double time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
