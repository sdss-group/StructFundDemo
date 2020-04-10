package com.newtouch.structfund.entity;

import lombok.Data;

import java.util.Date;

/**
 *  产品销售渠道
 * @author shoabo 2020-04-07
 */
@Data
public class FundSaleChannel {

    /**
     * 产品代码
     */
    private String fundCode;

    /**
     * 登记机构代码
     */
    private String registerCode;

    /**
     * 产品名称
     */
    private String fundName;

    /**
     * 个人网银
     */
    private String personalBank;

    /**
     * 电话银行
     */
    private String telephoneBank;

    /**
     * 手机银行
     */
    private String mobilephoneBank;

    /**
     * 柜面
     */
    private String counter;

    /**
     * 地柜
     */
    private String lowCabinet;

    /**
     * 家易通
     */
    private String jiayitong;

    /**
     * 门户网站
     */
    private String webPortal;

    /**
     * 多媒体
     */
    private String multiMedia;

    /**
     * ITM
     */
    private String itm;

    /**
     * 自助通
     */
    private String zizhutong;

    /**
     * 智能柜
     */
    private String intelligenceCabinet;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 修改日期
     */
    private Date updateDate;

    /**
     * 用户Id
     */
    private Integer userId;



}
