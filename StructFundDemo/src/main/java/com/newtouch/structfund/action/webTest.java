package com.newtouch.structfund.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newtouch.structfund.mapper.testMapper;

@Controller
public class webTest {
	
	@Autowired
	testMapper mapper;
	
	@ResponseBody
	@RequestMapping("test")
	public String test() {
		mapper.test();
		return "success";
	}

}
