package com.newtouch.structfund.entity;

import java.sql.Timestamp;

//产品客户群表
public class FundCusttype {

    // TA代码
    private String registerCode;

    //  产品代码
    private String fundCode;

    //  客户群类型
    private String custType;

    //  创建时间
    private Timestamp timestampC;

    //   更新时间
    private Timestamp timestampU;

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

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public Timestamp getTimestampC() {
        return timestampC;
    }

    public void setTimestampC(Timestamp timestampC) {
        this.timestampC = timestampC;
    }

    public Timestamp getTimestampU() {
        return timestampU;
    }

    public void setTimestampU(Timestamp timestampU) {
        this.timestampU = timestampU;
    }
}
