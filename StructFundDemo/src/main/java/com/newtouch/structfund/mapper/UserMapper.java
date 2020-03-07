package com.newtouch.structfund.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.newtouch.structfund.entity.User;

@Mapper
public interface UserMapper {
	
	public User queryUser(User user) throws Exception;

}
