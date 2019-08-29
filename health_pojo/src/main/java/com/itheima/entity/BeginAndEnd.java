package com.itheima.entity;

import java.util.Date;

public class BeginAndEnd {
    private Date value1;
    private Date value2;

    public BeginAndEnd(Date value1, Date value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public String toString() {
        return "BeginAndEnd{" +
                "value1=" + value1 +
                ", value2=" + value2 +
                '}';
    }

    public Date getValue1() {
        return value1;
    }

    public void setValue1(Date value1) {
        this.value1 = value1;
    }

    public Date getValue2() {
        return value2;
    }

    public void setValue2(Date value2) {
        this.value2 = value2;
    }
}
