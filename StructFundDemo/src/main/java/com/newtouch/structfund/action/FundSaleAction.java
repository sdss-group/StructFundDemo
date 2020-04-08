package com.newtouch.structfund.action;

import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.newtouch.structfund.entity.FundSale;
import com.newtouch.structfund.entity.User;
import com.newtouch.structfund.mapper.FundSaleMapper;

@RestController
@RequestMapping("fundSale")
@SuppressWarnings("all")
@Transactional
public class FundSaleAction {
	
	@Autowired
	FundSaleMapper fundSaleMapper;
	
	@PostMapping("queryFundSaleInfo")
	public Map queryFundSaleInfo(HttpServletRequest req,Integer currentPage,Integer pageSize){
		Map param=new HashMap();
		String registerCode=req.getParameter("registerCode");
		String fundCode=req.getParameter("fundCode");
		String ipoStartDate=req.getParameter("ipoStartDate");
		String ipoEndDate=req.getParameter("ipoEndDate");
		
		param.put("registerCode", registerCode);
		param.put("fundCode", fundCode);
		param.put("ipoStartDate", ipoStartDate);
		param.put("ipoEndDate", ipoEndDate);
		param.put("startRow", (currentPage-1)*pageSize);
		param.put("pageSize", pageSize);
		List<FundSale> resultList = fundSaleMapper.queryFundSaleInfo(param);
		Integer totalRows=fundSaleMapper.count(param);
		Map resultMap=new HashMap();
		resultMap.put("dataList", resultList);
		resultMap.put("totalRows", totalRows);
		return resultMap;
		
	}
	
	@PostMapping("addFundSaleInfo")
	public String addFundSaleInfo(FundSale fundSale,HttpSession session) {
		User loginUser=(User) session.getAttribute("userInfo");
//		String userName=loginUser.getUsername();
//		fundSale.setOperator(userName);
		Integer result = fundSaleMapper.insertSelective(fundSale);
		
		return result==1 ? "success":"failed";
		
		
	}
	
	@PostMapping("modifyFundSaleInfo")
	public String modifyFundSaleInfo(FundSale fundSale,HttpSession session) {
		fundSale.setTimestampU(new Timestamp(System.currentTimeMillis()));
		Integer result=fundSaleMapper.updateByPrimaryKeySelective(fundSale);
		
		return result==1 ? "success":"failed";
	}
	
	@PostMapping("deleteFundSale")
	public String deleteFundSale(@RequestBody List<FundSale> params) {
		Integer result=fundSaleMapper.deleteFundSale(params);
		
		return result==params.size() ? "success":"failed";
	}

}
