package com.newtouch.structfund.mapper;

import com.newtouch.structfund.entity.RegFundVo;

import java.util.List;

public interface RegFundQueryMapper {

    public List<RegFundVo> getRegList();

    public List<RegFundVo> getFundList();

}
