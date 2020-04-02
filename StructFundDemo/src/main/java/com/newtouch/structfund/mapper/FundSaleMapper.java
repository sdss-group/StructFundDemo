package com.newtouch.structfund.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.newtouch.structfund.entity.FundSale;

@Mapper
public interface FundSaleMapper {
	
	public List<FundSale> queryFundSaleInfo(Map map);
	
	public Integer count(Map map);

}
