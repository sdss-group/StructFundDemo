package com.newtouch.structfund.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class FundSale{
	
	private String registerCode;

    private String fundCode;
	
    private String transferType;

    private String charge;

    private String checkType;

    private String delayedEntry;

    private String investTerm;

    private String periodSubsFlag;

    private BigDecimal failTimesPs;

    private String subsCapType;

    private String purchCapType;

    private String fundLevel;

    private BigDecimal minBala;

    private BigDecimal maxBala;

    private String incomeType;

    private String saleObject;

    private String amtFlag;
    
    private Date setupDate;
    
    private Date endDate;
    
    private Date startTradeDate;
    
    private Date fundSellDate;
    
    private String fundAttr;
    
    public String getFundAttr() {
		return fundAttr;
	}

	public void setFundAttr(String fundAttr) {
		this.fundAttr = fundAttr;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartTradeDate() {
		return startTradeDate;
	}

	public void setStartTradeDate(Date startTradeDate) {
		this.startTradeDate = startTradeDate;
	}

	public Date getFundSellDate() {
		return fundSellDate;
	}

	public void setFundSellDate(Date fundSellDate) {
		this.fundSellDate = fundSellDate;
	}

    public Date getSetupDate() {
		return setupDate;
	}

	public void setSetupDate(Date setupDate) {
		this.setupDate = setupDate;
	}

	private String fundType;
	
	private String largeRedeFlag;

	public String getLargeRedeFlag() {
		return largeRedeFlag;
	}

	public void setLargeRedeFlag(String largeRedeFlag) {
		this.largeRedeFlag = largeRedeFlag;
	}

	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	private String fundCtrlFlag;

    private String tradeStart;

    private String tradeEnd;

    private BigDecimal untargetBalance;

    private BigDecimal saleRateFee;

    private String subsStart;

    private String subsEnd;

    private String performNum;

    private BigDecimal maxRatio;

    private BigDecimal minRatio;

    private String fastFlag;

    private String operator;

    private String authorizer;
    
    private String registerName;
    
    private String fundName;
    
    private Timestamp timestampU;
    
    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public void setregisterName(String registerName) {
        this.registerName = registerName;
    }

    public String getRegisterName() {
        return registerName;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getDelayedEntry() {
        return delayedEntry;
    }

    public void setDelayedEntry(String delayedEntry) {
        this.delayedEntry = delayedEntry;
    }

    public String getInvestTerm() {
        return investTerm;
    }

    public void setInvestTerm(String investTerm) {
        this.investTerm = investTerm;
    }

    public String getPeriodSubsFlag() {
        return periodSubsFlag;
    }

    public void setPeriodSubsFlag(String periodSubsFlag) {
        this.periodSubsFlag = periodSubsFlag;
    }

    public BigDecimal getFailTimesPs() {
        return failTimesPs;
    }

    public void setFailTimesPs(BigDecimal failTimesPs) {
        this.failTimesPs = failTimesPs;
    }

    public String getSubsCapType() {
        return subsCapType;
    }

    public void setSubsCapType(String subsCapType) {
        this.subsCapType = subsCapType;
    }

    public String getPurchCapType() {
        return purchCapType;
    }

    public void setPurchCapType(String purchCapType) {
        this.purchCapType = purchCapType;
    }

    public String getFundLevel() {
        return fundLevel;
    }

    public void setFundLevel(String fundLevel) {
        this.fundLevel = fundLevel;
    }

    public BigDecimal getMinBala() {
        return minBala;
    }

    public void setMinBala(BigDecimal minBala) {
        this.minBala = minBala;
    }

    public BigDecimal getMaxBala() {
        return maxBala;
    }

    public void setMaxBala(BigDecimal maxBala) {
        this.maxBala = maxBala;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public String getSaleObject() {
        return saleObject;
    }

    public void setSaleObject(String saleObject) {
        this.saleObject = saleObject;
    }

    public String getAmtFlag() {
        return amtFlag;
    }

    public void setAmtFlag(String amtFlag) {
        this.amtFlag = amtFlag;
    }

    public String getFundCtrlFlag() {
        return fundCtrlFlag;
    }

    public void setFundCtrlFlag(String fundCtrlFlag) {
        this.fundCtrlFlag = fundCtrlFlag;
    }

    public String getTradeStart() {
        return tradeStart;
    }

    public void setTradeStart(String tradeStart) {
        this.tradeStart = tradeStart;
    }

    public String getTradeEnd() {
        return tradeEnd;
    }

    public void setTradeEnd(String tradeEnd) {
        this.tradeEnd = tradeEnd;
    }

    public BigDecimal getUntargetBalance() {
        return untargetBalance;
    }

    public void setUntargetBalance(BigDecimal untargetBalance) {
        this.untargetBalance = untargetBalance;
    }

    public BigDecimal getSaleRateFee() {
        return saleRateFee;
    }

    public void setSaleRateFee(BigDecimal saleRateFee) {
        this.saleRateFee = saleRateFee;
    }

    public String getSubsStart() {
        return subsStart;
    }

    public void setSubsStart(String subsStart) {
        this.subsStart = subsStart;
    }

    public String getSubsEnd() {
        return subsEnd;
    }

    public void setSubsEnd(String subsEnd) {
        this.subsEnd = subsEnd;
    }

    public String getPerformNum() {
        return performNum;
    }

    public void setPerformNum(String performNum) {
        this.performNum = performNum;
    }

    public BigDecimal getMaxRatio() {
        return maxRatio;
    }

    public void setMaxRatio(BigDecimal maxRatio) {
        this.maxRatio = maxRatio;
    }

    public BigDecimal getMinRatio() {
        return minRatio;
    }

    public void setMinRatio(BigDecimal minRatio) {
        this.minRatio = minRatio;
    }

    public String getFastFlag() {
        return fastFlag;
    }

    public void setFastFlag(String fastFlag) {
        this.fastFlag = fastFlag;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getAuthorizer() {
        return authorizer;
    }

    public void setAuthorizer(String authorizer) {
        this.authorizer = authorizer;
    }

	public Timestamp getTimestampU() {
		return timestampU;
	}

	public void setTimestampU(Timestamp timestampU) {
		this.timestampU = timestampU;
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

	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}
}