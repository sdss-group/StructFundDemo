package com.newtouch.structfund.mapper;

import com.newtouch.structfund.entity.FundCusttype;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface FundCusttypeMapper {

    List<FundCusttype> listAll();
}
