package com.newtouch.structfund.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.newtouch.structfund.entity.CostParameters;

@Mapper
public interface CostMapper {
	public List<CostParameters> costSelect(Map param) throws Exception;

	public CostParameters costSelectBy(CostParameters costParameters) throws Exception;

	public void costDelete(CostParameters costParameters) throws Exception;

	public int costUpdate(CostParameters costParameters) throws Exception;

	public void costAdd(CostParameters costParameters) throws Exception;

	public Integer count(Map param) throws Exception;


}
