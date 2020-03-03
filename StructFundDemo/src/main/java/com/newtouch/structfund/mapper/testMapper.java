package com.newtouch.structfund.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface testMapper {

	@Select(value="update account_book set BUSINESS_CODE ='100' ")
	public void test();
	
}
