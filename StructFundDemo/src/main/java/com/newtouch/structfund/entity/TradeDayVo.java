package com.newtouch.structfund.entity;

import lombok.Data;

@Data
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




}
