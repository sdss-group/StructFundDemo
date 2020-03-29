package com.newtouch.structfund.entity;

public class FundReg {

    // 注册登记人代码
    private String registerCode;

    //产品代码
    private String fundCode;

    // 注册登记人名称
    private String registerName;

    // 指定交易标志
    private String assignFlag;

    // 强制开户标志
    private String forceOpen;

    // 个人开户证件类型
    private String indiOpenCerttype;

    // 机构开户证件类型
    private String instOpenCerttype;

    // 允许未成年人交易
    private String minnorTrade;

    // 地址
    private String addr;

    // 邮编
    private String postCode;

    // 联系电话
    private String tel;

    // 传真号码
    private String fax;

    // 联系人
    private String linkMan;

    // 成立时间
    private String foundDate;

    // 行内TA标志
    private String flag;

    // 备注
    private String remark;

    // 操作人
    private String operator;

    // 授权人
    private String authorizer;

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public String getAssignFlag() {
        return assignFlag;
    }

    public void setAssignFlag(String assignFlag) {
        this.assignFlag = assignFlag;
    }

    public String getForceOpen() {
        return forceOpen;
    }

    public void setForceOpen(String forceOpen) {
        this.forceOpen = forceOpen;
    }

    public String getIndiOpenCerttype() {
        return indiOpenCerttype;
    }

    public void setIndiOpenCerttype(String indiOpenCerttype) {
        this.indiOpenCerttype = indiOpenCerttype;
    }

    public String getInstOpenCerttype() {
        return instOpenCerttype;
    }

    public void setInstOpenCerttype(String instOpenCerttype) {
        this.instOpenCerttype = instOpenCerttype;
    }

    public String getMinnorTrade() {
        return minnorTrade;
    }

    public void setMinnorTrade(String minnorTrade) {
        this.minnorTrade = minnorTrade;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(String foundDate) {
        this.foundDate = foundDate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getFundCode() { return fundCode; }

    public void setFundCode(String fundCode) { this.fundCode = fundCode; }
}
