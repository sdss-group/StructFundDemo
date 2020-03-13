package com.newtouch.structfund.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newtouch.structfund.entity.FundSet;
import com.newtouch.structfund.mapper.FundSetMapper;

@Controller
@RequestMapping("/baseinfo")
@ResponseBody
public class BaseinfoAction {
	
	@Autowired
	FundSetMapper fundSetMapper;
	
	@RequestMapping("/queryBaseinfo")
	public List queryBaseinfo(FundSet baseinfo) {
		return fundSetMapper.queryBaseinfo(baseinfo);
	}

}
