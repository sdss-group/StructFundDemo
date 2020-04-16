package com.newtouch.structfund.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SaleBranchDate implements Serializable {

    public String orgCode;

    public String orgName;

    public String registerCode;

    public String fundCode;

    private String fundName;

    public String status;

    private String branceName;
    //允许销售  1 允许  0 不允许
    private String perSale;

    private Date timestampC;

    private Date timestampU;

    public List<String> getOrgCodes() {
        return orgCodes;
    }

    public void setOrgCodes(List<String> orgCodes) {
        this.orgCodes = orgCodes;
    }

    private List<String> orgCodes;

    private  List<OrgAmt> orgAmts;

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

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

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBranceName() {
        return branceName;
    }

    public void setBranceName(String branceName) {
        this.branceName = branceName;
    }

    public String getPerSale() {
        return perSale;
    }

    public void setPerSale(String perSale) {
        this.perSale = perSale;
    }

    public List<OrgAmt> getOrgAmts() {
        return orgAmts;
    }

    public void setOrgAmts(List<OrgAmt> orgAmts) {
        this.orgAmts = orgAmts;
    }

    public Date getTimestampC() {
        return timestampC;
    }

    public void setTimestampC(Date timestampC) {
        this.timestampC = timestampC;
    }

    public Date getTimestampU() {
        return timestampU;
    }

    public void setTimestampU(Date timestampU) {
        this.timestampU = timestampU;
    }
}
