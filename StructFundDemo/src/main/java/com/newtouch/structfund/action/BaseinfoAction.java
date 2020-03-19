package com.newtouch.structfund.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.newtouch.structfund.entity.FundSet;
import com.newtouch.structfund.mapper.FundSetMapper;

@Controller
@RequestMapping("/baseinfo")
@ResponseBody
@SuppressWarnings("all")
public class BaseinfoAction {
	
	@Autowired
	FundSetMapper fundSetMapper;
	
	
	@RequestMapping("/queryBaseinfo")
	public Map queryBaseinfo(FundSet baseinfo,Integer currentPage,Integer pageSize) {
		Map param=new HashMap();
		System.out.println(baseinfo.getRegisterCode());
		param.put("baseinfo", baseinfo);
		param.put("startRow", (currentPage-1)*pageSize);
		param.put("pageSize", pageSize);
		List<FundSet> resultList=fundSetMapper.queryBaseinfo(param);
		for (FundSet fundSet : resultList) {
			System.out.println(fundSet.getFundCode()+":"+ fundSet.getFundStatus());
		}
		Integer totalRows=fundSetMapper.count(param);
		Map resultMap=new HashMap();
		resultMap.put("dataList", resultList);
		System.out.println("fundstatus:"+resultList.get(0).getFundStatus());
		resultMap.put("totalRows", totalRows);
		return resultMap;
	}

	
}
