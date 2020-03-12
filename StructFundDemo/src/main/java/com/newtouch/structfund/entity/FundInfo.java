package com.newtouch.structfund.entity;

import java.math.BigDecimal;
import java.util.Date;

public class FundInfo{
	
	private String registerCode;

    private String fundCode;
	
    private String fundName;

    private BigDecimal totalFundVol;

    private String fundStatus;

    private BigDecimal nav;

    private Date psDate;

    private Date dividentDate;

    private Date xrDate;

    private Date registDate;

    private String indiIssueType;

    private String instIssueType;

    private String subsType;

    private String collectFeeType;

    private BigDecimal tradPrice;

    private Date nextTradeDate;

    private BigDecimal valueLine;

    private String netValueType;

    private BigDecimal accumulativeNav;

    private String convertStatus;

    private String periodicStatus;

    private String transferAgenStatus;

    private String announcFlag;

    private BigDecimal totalDivident;

    private BigDecimal fundIncome;

    private String fundIncomeFlag;

    private BigDecimal yield;

    private String yieldFlag;

    private BigDecimal guarantNav;

    private BigDecimal yearIncome;

    private String yearIncomeFlag;

    private String dayIncomeFlag;

    private BigDecimal dayIncome;

    private Date updateDate;

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public BigDecimal getTotalFundVol() {
        return totalFundVol;
    }

    public void setTotalFundVol(BigDecimal totalFundVol) {
        this.totalFundVol = totalFundVol;
    }

    public String getFundStatus() {
        return fundStatus;
    }

    public void setFundStatus(String fundStatus) {
        this.fundStatus = fundStatus;
    }

    public BigDecimal getNav() {
        return nav;
    }

    public void setNav(BigDecimal nav) {
        this.nav = nav;
    }

    public Date getPsDate() {
        return psDate;
    }

    public void setPsDate(Date psDate) {
        this.psDate = psDate;
    }

    public Date getDividentDate() {
        return dividentDate;
    }

    public void setDividentDate(Date dividentDate) {
        this.dividentDate = dividentDate;
    }

    public Date getXrDate() {
        return xrDate;
    }

    public void setXrDate(Date xrDate) {
        this.xrDate = xrDate;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public String getIndiIssueType() {
        return indiIssueType;
    }

    public void setIndiIssueType(String indiIssueType) {
        this.indiIssueType = indiIssueType;
    }

    public String getInstIssueType() {
        return instIssueType;
    }

    public void setInstIssueType(String instIssueType) {
        this.instIssueType = instIssueType;
    }

    public String getSubsType() {
        return subsType;
    }

    public void setSubsType(String subsType) {
        this.subsType = subsType;
    }

    public String getCollectFeeType() {
        return collectFeeType;
    }

    public void setCollectFeeType(String collectFeeType) {
        this.collectFeeType = collectFeeType;
    }

    public BigDecimal getTradPrice() {
        return tradPrice;
    }

    public void setTradPrice(BigDecimal tradPrice) {
        this.tradPrice = tradPrice;
    }

    public Date getNextTradeDate() {
        return nextTradeDate;
    }

    public void setNextTradeDate(Date nextTradeDate) {
        this.nextTradeDate = nextTradeDate;
    }

    public BigDecimal getValueLine() {
        return valueLine;
    }

    public void setValueLine(BigDecimal valueLine) {
        this.valueLine = valueLine;
    }

    public String getNetValueType() {
        return netValueType;
    }

    public void setNetValueType(String netValueType) {
        this.netValueType = netValueType;
    }

    public BigDecimal getAccumulativeNav() {
        return accumulativeNav;
    }

    public void setAccumulativeNav(BigDecimal accumulativeNav) {
        this.accumulativeNav = accumulativeNav;
    }

    public String getConvertStatus() {
        return convertStatus;
    }

    public void setConvertStatus(String convertStatus) {
        this.convertStatus = convertStatus;
    }

    public String getPeriodicStatus() {
        return periodicStatus;
    }

    public void setPeriodicStatus(String periodicStatus) {
        this.periodicStatus = periodicStatus;
    }

    public String getTransferAgenStatus() {
        return transferAgenStatus;
    }

    public void setTransferAgenStatus(String transferAgenStatus) {
        this.transferAgenStatus = transferAgenStatus;
    }

    public String getAnnouncFlag() {
        return announcFlag;
    }

    public void setAnnouncFlag(String announcFlag) {
        this.announcFlag = announcFlag;
    }

    public BigDecimal getTotalDivident() {
        return totalDivident;
    }

    public void setTotalDivident(BigDecimal totalDivident) {
        this.totalDivident = totalDivident;
    }

    public BigDecimal getFundIncome() {
        return fundIncome;
    }

    public void setFundIncome(BigDecimal fundIncome) {
        this.fundIncome = fundIncome;
    }

    public String getFundIncomeFlag() {
        return fundIncomeFlag;
    }

    public void setFundIncomeFlag(String fundIncomeFlag) {
        this.fundIncomeFlag = fundIncomeFlag;
    }

    public BigDecimal getYield() {
        return yield;
    }

    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    public String getYieldFlag() {
        return yieldFlag;
    }

    public void setYieldFlag(String yieldFlag) {
        this.yieldFlag = yieldFlag;
    }

    public BigDecimal getGuarantNav() {
        return guarantNav;
    }

    public void setGuarantNav(BigDecimal guarantNav) {
        this.guarantNav = guarantNav;
    }

    public BigDecimal getYearIncome() {
        return yearIncome;
    }

    public void setYearIncome(BigDecimal yearIncome) {
        this.yearIncome = yearIncome;
    }

    public String getYearIncomeFlag() {
        return yearIncomeFlag;
    }

    public void setYearIncomeFlag(String yearIncomeFlag) {
        this.yearIncomeFlag = yearIncomeFlag;
    }

    public String getDayIncomeFlag() {
        return dayIncomeFlag;
    }

    public void setDayIncomeFlag(String dayIncomeFlag) {
        this.dayIncomeFlag = dayIncomeFlag;
    }

    public BigDecimal getDayIncome() {
        return dayIncome;
    }

    public void setDayIncome(BigDecimal dayIncome) {
        this.dayIncome = dayIncome;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
}