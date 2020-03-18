package com.newtouch.structfund.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import com.newtouch.structfund.entity.FundSet;

@Mapper
public interface FundSetMapper {

	public List<FundSet> queryBaseinfo(Map param);
	
	public Integer count(Map param);
}