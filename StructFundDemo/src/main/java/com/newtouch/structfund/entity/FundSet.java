package com.newtouch.structfund.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class FundSet {
	
	private String registerCode;

    private String fundCode;
	
    private String fundName;

    private String fundNameAbbr;

    private String fundSponsor;

    private BigDecimal faceValue;

    private String shareClass;

    private String currencyType;

    private String cashFlag;

    private String incomeCurrType;

    private String costCurrType;

    private BigDecimal issuePrice;

    private String fundType;

    @DateTimeFormat(pattern = "yyyyMMdd")
    private Date ipoStartDate;

    @DateTimeFormat(pattern = "yyyyMMdd")
    private Date ipoEndDate;

    private Date setupDate;

    private Date alimitDay;

    private Date startDate;

    private Date endDate;

    private String saleObject;

    private BigDecimal maxRedeem;

    private String interProperty;

    private Integer interDate;

    private String subBasic;

    private Integer incomeInterDate;

    private String tradeType;

    private String bonusType;

    private String redeReturnType;

    private String breakEven;

    private String benefitGuarantee;

    private String ipoType;

    private String fundTrustee;

    private String breachRede;

    private String debtFundType;

    private String clientGroup;

    private String fundRegistCode;

    private String cancelType;

    private BigDecimal breaRedeFee;

    private String partRede;

    private BigDecimal excessiveRatio;

    private BigDecimal interestRate;

    private BigDecimal redeFeeBackRatio;

    private Integer lockDays;

    private String isCheckInvester;

    private Date updateDate;
    
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
    
    private BigDecimal maxRedeDay;

    private BigDecimal indiMaxRedeDayFast;

    private BigDecimal maxPeriodSub;

    private BigDecimal indiUnitSubsVol;

    private BigDecimal indiUnitSubsAmt;

    private BigDecimal indiRedeemBase;

    private BigDecimal indiAddSubsVol;

    private BigDecimal indiAddSubsAmt;

    private BigDecimal indiAddBidsAmt;

    private BigDecimal indiMinSubsVol;

    private BigDecimal indiMinSubsAmt;

    private BigDecimal indiMinBidsAmt;

    private BigDecimal indiMaxSubsVol;

    private BigDecimal indiMaxSubsAmt;

    private BigDecimal indiMaxBidsAmt;

    private BigDecimal indiDayMaxBuy;

    private BigDecimal indiMinRedeVol;

    private BigDecimal indiMaxRede;

    private BigDecimal indiDayMaxRede;

    private BigDecimal indiMinConvVol;

    private BigDecimal indiMinPs;

    private BigDecimal indiMinBalance;
    
    private String registerName;

    public void setregisterName(String registerName) {
        this.registerName = registerName;
    }

    public String getRegisterName() {
        return registerName;
    }
    

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getFundNameAbbr() {
        return fundNameAbbr;
    }

    public void setFundNameAbbr(String fundNameAbbr) {
        this.fundNameAbbr = fundNameAbbr;
    }

    public String getFundSponsor() {
        return fundSponsor;
    }

    public void setFundSponsor(String fundSponsor) {
        this.fundSponsor = fundSponsor;
    }

    public BigDecimal getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }

    public String getShareClass() {
        return shareClass;
    }

    public void setShareClass(String shareClass) {
        this.shareClass = shareClass;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getCashFlag() {
        return cashFlag;
    }

    public void setCashFlag(String cashFlag) {
        this.cashFlag = cashFlag;
    }

    public String getIncomeCurrType() {
        return incomeCurrType;
    }

    public void setIncomeCurrType(String incomeCurrType) {
        this.incomeCurrType = incomeCurrType;
    }

    public String getCostCurrType() {
        return costCurrType;
    }

    public void setCostCurrType(String costCurrType) {
        this.costCurrType = costCurrType;
    }

    public BigDecimal getIssuePrice() {
        return issuePrice;
    }

    public void setIssuePrice(BigDecimal issuePrice) {
        this.issuePrice = issuePrice;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public Date getIpoStartDate() {
        return ipoStartDate;
    }

    public void setIpoStartDate(Date ipoStartDate) {
        this.ipoStartDate = ipoStartDate;
    }

    public Date getIpoEndDate() {
        return ipoEndDate;
    }

    public void setIpoEndDate(Date ipoEndDate) {
        this.ipoEndDate = ipoEndDate;
    }

    public Date getSetupDate() {
        return setupDate;
    }

    public void setSetupDate(Date setupDate) {
        this.setupDate = setupDate;
    }

    public Date getAlimitDay() {
        return alimitDay;
    }

    public void setAlimitDay(Date alimitDay) {
        this.alimitDay = alimitDay;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSaleObject() {
        return saleObject;
    }

    public void setSaleObject(String saleObject) {
        this.saleObject = saleObject;
    }

    public BigDecimal getMaxRedeem() {
        return maxRedeem;
    }

    public void setMaxRedeem(BigDecimal maxRedeem) {
        this.maxRedeem = maxRedeem;
    }

    public String getInterProperty() {
        return interProperty;
    }

    public void setInterProperty(String interProperty) {
        this.interProperty = interProperty;
    }

    public Integer getInterDate() {
        return interDate;
    }

    public void setInterDate(Integer interDate) {
        this.interDate = interDate;
    }

    public String getSubBasic() {
        return subBasic;
    }

    public void setSubBasic(String subBasic) {
        this.subBasic = subBasic;
    }

    public Integer getIncomeInterDate() {
        return incomeInterDate;
    }

    public void setIncomeInterDate(Integer incomeInterDate) {
        this.incomeInterDate = incomeInterDate;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getBonusType() {
        return bonusType;
    }

    public void setBonusType(String bonusType) {
        this.bonusType = bonusType;
    }

    public String getRedeReturnType() {
        return redeReturnType;
    }

    public void setRedeReturnType(String redeReturnType) {
        this.redeReturnType = redeReturnType;
    }

    public String getBreakEven() {
        return breakEven;
    }

    public void setBreakEven(String breakEven) {
        this.breakEven = breakEven;
    }

    public String getBenefitGuarantee() {
        return benefitGuarantee;
    }

    public void setBenefitGuarantee(String benefitGuarantee) {
        this.benefitGuarantee = benefitGuarantee;
    }

    public String getIpoType() {
        return ipoType;
    }

    public void setIpoType(String ipoType) {
        this.ipoType = ipoType;
    }

    public String getFundTrustee() {
        return fundTrustee;
    }

    public void setFundTrustee(String fundTrustee) {
        this.fundTrustee = fundTrustee;
    }

    public String getBreachRede() {
        return breachRede;
    }

    public void setBreachRede(String breachRede) {
        this.breachRede = breachRede;
    }

    public String getDebtFundType() {
        return debtFundType;
    }

    public void setDebtFundType(String debtFundType) {
        this.debtFundType = debtFundType;
    }

    public String getClientGroup() {
        return clientGroup;
    }

    public void setClientGroup(String clientGroup) {
        this.clientGroup = clientGroup;
    }

    public String getFundRegistCode() {
        return fundRegistCode;
    }

    public void setFundRegistCode(String fundRegistCode) {
        this.fundRegistCode = fundRegistCode;
    }

    public String getCancelType() {
        return cancelType;
    }

    public void setCancelType(String cancelType) {
        this.cancelType = cancelType;
    }

    public BigDecimal getBreaRedeFee() {
        return breaRedeFee;
    }

    public void setBreaRedeFee(BigDecimal breaRedeFee) {
        this.breaRedeFee = breaRedeFee;
    }

    public String getPartRede() {
        return partRede;
    }

    public void setPartRede(String partRede) {
        this.partRede = partRede;
    }

    public BigDecimal getExcessiveRatio() {
        return excessiveRatio;
    }

    public void setExcessiveRatio(BigDecimal excessiveRatio) {
        this.excessiveRatio = excessiveRatio;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getRedeFeeBackRatio() {
        return redeFeeBackRatio;
    }

    public void setRedeFeeBackRatio(BigDecimal redeFeeBackRatio) {
        this.redeFeeBackRatio = redeFeeBackRatio;
    }

    public Integer getLockDays() {
        return lockDays;
    }

    public void setLockDays(Integer lockDays) {
        this.lockDays = lockDays;
    }

    public String getIsCheckInvester() {
        return isCheckInvester;
    }

    public void setIsCheckInvester(String isCheckInvester) {
        this.isCheckInvester = isCheckInvester;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    //transctrl
    public BigDecimal getMaxRedeDay() {
        return maxRedeDay;
    }

    public void setMaxRedeDay(BigDecimal maxRedeDay) {
        this.maxRedeDay = maxRedeDay;
    }

    public BigDecimal getIndiMaxRedeDayFast() {
        return indiMaxRedeDayFast;
    }

    public void setIndiMaxRedeDayFast(BigDecimal indiMaxRedeDayFast) {
        this.indiMaxRedeDayFast = indiMaxRedeDayFast;
    }

    public BigDecimal getMaxPeriodSub() {
        return maxPeriodSub;
    }

    public void setMaxPeriodSub(BigDecimal maxPeriodSub) {
        this.maxPeriodSub = maxPeriodSub;
    }

    public BigDecimal getIndiUnitSubsVol() {
        return indiUnitSubsVol;
    }

    public void setIndiUnitSubsVol(BigDecimal indiUnitSubsVol) {
        this.indiUnitSubsVol = indiUnitSubsVol;
    }

    public BigDecimal getIndiUnitSubsAmt() {
        return indiUnitSubsAmt;
    }

    public void setIndiUnitSubsAmt(BigDecimal indiUnitSubsAmt) {
        this.indiUnitSubsAmt = indiUnitSubsAmt;
    }

    public BigDecimal getIndiRedeemBase() {
        return indiRedeemBase;
    }

    public void setIndiRedeemBase(BigDecimal indiRedeemBase) {
        this.indiRedeemBase = indiRedeemBase;
    }

    public BigDecimal getIndiAddSubsVol() {
        return indiAddSubsVol;
    }

    public void setIndiAddSubsVol(BigDecimal indiAddSubsVol) {
        this.indiAddSubsVol = indiAddSubsVol;
    }

    public BigDecimal getIndiAddSubsAmt() {
        return indiAddSubsAmt;
    }

    public void setIndiAddSubsAmt(BigDecimal indiAddSubsAmt) {
        this.indiAddSubsAmt = indiAddSubsAmt;
    }

    public BigDecimal getIndiAddBidsAmt() {
        return indiAddBidsAmt;
    }

    public void setIndiAddBidsAmt(BigDecimal indiAddBidsAmt) {
        this.indiAddBidsAmt = indiAddBidsAmt;
    }

    public BigDecimal getIndiMinSubsVol() {
        return indiMinSubsVol;
    }

    public void setIndiMinSubsVol(BigDecimal indiMinSubsVol) {
        this.indiMinSubsVol = indiMinSubsVol;
    }

    public BigDecimal getIndiMinSubsAmt() {
        return indiMinSubsAmt;
    }

    public void setIndiMinSubsAmt(BigDecimal indiMinSubsAmt) {
        this.indiMinSubsAmt = indiMinSubsAmt;
    }

    public BigDecimal getIndiMinBidsAmt() {
        return indiMinBidsAmt;
    }

    public void setIndiMinBidsAmt(BigDecimal indiMinBidsAmt) {
        this.indiMinBidsAmt = indiMinBidsAmt;
    }

    public BigDecimal getIndiMaxSubsVol() {
        return indiMaxSubsVol;
    }

    public void setIndiMaxSubsVol(BigDecimal indiMaxSubsVol) {
        this.indiMaxSubsVol = indiMaxSubsVol;
    }

    public BigDecimal getIndiMaxSubsAmt() {
        return indiMaxSubsAmt;
    }

    public void setIndiMaxSubsAmt(BigDecimal indiMaxSubsAmt) {
        this.indiMaxSubsAmt = indiMaxSubsAmt;
    }

    public BigDecimal getIndiMaxBidsAmt() {
        return indiMaxBidsAmt;
    }

    public void setIndiMaxBidsAmt(BigDecimal indiMaxBidsAmt) {
        this.indiMaxBidsAmt = indiMaxBidsAmt;
    }

    public BigDecimal getIndiDayMaxBuy() {
        return indiDayMaxBuy;
    }

    public void setIndiDayMaxBuy(BigDecimal indiDayMaxBuy) {
        this.indiDayMaxBuy = indiDayMaxBuy;
    }

    public BigDecimal getIndiMinRedeVol() {
        return indiMinRedeVol;
    }

    public void setIndiMinRedeVol(BigDecimal indiMinRedeVol) {
        this.indiMinRedeVol = indiMinRedeVol;
    }

    public BigDecimal getIndiMaxRede() {
        return indiMaxRede;
    }

    public void setIndiMaxRede(BigDecimal indiMaxRede) {
        this.indiMaxRede = indiMaxRede;
    }

    public BigDecimal getIndiDayMaxRede() {
        return indiDayMaxRede;
    }

    public void setIndiDayMaxRede(BigDecimal indiDayMaxRede) {
        this.indiDayMaxRede = indiDayMaxRede;
    }

    public BigDecimal getIndiMinConvVol() {
        return indiMinConvVol;
    }

    public void setIndiMinConvVol(BigDecimal indiMinConvVol) {
        this.indiMinConvVol = indiMinConvVol;
    }

    public BigDecimal getIndiMinPs() {
        return indiMinPs;
    }

    public void setIndiMinPs(BigDecimal indiMinPs) {
        this.indiMinPs = indiMinPs;
    }

    public BigDecimal getIndiMinBalance() {
        return indiMinBalance;
    }

    public void setIndiMinBalance(BigDecimal indiMinBalance) {
        this.indiMinBalance = indiMinBalance;
    }
    //info
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