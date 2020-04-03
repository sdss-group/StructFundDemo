package com.newtouch.structfund.entity;

import java.math.BigDecimal;
import java.util.Date;

//总行销售额度
public class OrgAmt {

    // 机构代码;  核心系统的分行代码
    private String orgCode;

    // 注册登记机构代码;
    private String registerCode;

    // 产品代码;
    private String fundCode;

    // 级别;  0-总行； 1-分行； 2-网点
    private String level;

    // 上级机构;
    private String orgSupeRbr;

    // 额度类型;  0-认购期，1-申购期
    private String type;

    // 可销售额度;  初始的总的额度，包含预留额度和已销售额度
    private BigDecimal totalAmt;

    // 预留额度;
    private BigDecimal resvAmt;

    // 生效标志;  0-不生效； 1-生效
    private String effectFlag;

    // 操作员代码;  操作员代码，默认为’000000’
    private String userCode;

    // 是否全行销售模式;
    private String channelVolFlag;

    // 电子渠道额度;  总行电子渠道或分行电子渠道设置的额度
    private BigDecimal channelVolAmt;

    private String bankvolFlag;

    private BigDecimal bankvolAmt;

    private Date timestampC;

    private Date timestampU;

    private String fundName;

    private String registerName;

    private String orgName;

    private Long saledAmt;

    private Long restAmt;

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getOrgSupeRbr() {
        return orgSupeRbr;
    }

    public void setOrgSupeRbr(String orgSupeRbr) {
        this.orgSupeRbr = orgSupeRbr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    public BigDecimal getResvAmt() {
        return resvAmt;
    }

    public void setResvAmt(BigDecimal resvAmt) {
        this.resvAmt = resvAmt;
    }

    public String getEffectFlag() {
        return effectFlag;
    }

    public void setEffectFlag(String effectFlag) {
        this.effectFlag = effectFlag;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getChannelVolFlag() {
        return channelVolFlag;
    }

    public void setChannelVolFlag(String channelVolFlag) {
        this.channelVolFlag = channelVolFlag;
    }

    public BigDecimal getChannelVolAmt() {
        return channelVolAmt;
    }

    public void setChannelVolAmt(BigDecimal channelVolAmt) {
        this.channelVolAmt = channelVolAmt;
    }

    public String getBankvolFlag() {
        return bankvolFlag;
    }

    public void setBankvolFlag(String bankvolFlag) {
        this.bankvolFlag = bankvolFlag;
    }

    public BigDecimal getBankvolAmt() {
        return bankvolAmt;
    }

    public void setBankvolAmt(BigDecimal bankvolAmt) {
        this.bankvolAmt = bankvolAmt;
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

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Long getSaledAmt() {
        return saledAmt;
    }

    public void setSaledAmt(Long saledAmt) {
        this.saledAmt = saledAmt;
    }

    public Long getRestAmt() {
        return restAmt;
    }

    public void setRestAmt(Long restAmt) {
        this.restAmt = restAmt;
    }
}
