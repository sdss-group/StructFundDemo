package com.newtouch.structfund.entity;

import lombok.Data;


public class TradeDayVo {
    //数据库本身需要的字段
    public String registerCode;
    public String fundCode;
    public String businessDate;
    public String flag;
    public String confirmDayFlag;

    //前端传过来的方便处理的字段
    public String defaultstatus;
    public String year;

    //数据库查询需要的字段
    public String beginDate;
    public String endDate;

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getConfirmDayFlag() {
        return confirmDayFlag;
    }

    public void setConfirmDayFlag(String confirmDayFlag) {
        this.confirmDayFlag = confirmDayFlag;
    }

    public String getDefaultstatus() {
        return defaultstatus;
    }

    public void setDefaultstatus(String defaultstatus) {
        this.defaultstatus = defaultstatus;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
