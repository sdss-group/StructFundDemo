package com.newtouch.structfund.mapper;

import com.newtouch.structfund.entity.FundReg;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface FundRegMapper {

    List<FundReg> queryAllAgencyAndProcode();

    List<FundReg> queryFundCode(String registerCode);

    FundReg queryAgency(String fundCode);


}
