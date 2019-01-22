package com.wanghuaxiang.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wanghuaxiang.demo.aop.annotation.NeedLogin;
import com.wanghuaxiang.demo.bean.User;
import com.wanghuaxiang.demo.repository.UserMapper;
import com.wanghuaxiang.demo.resources.property.PropertyUtil;

@RestController
@RequestMapping("/Test")
public class Test {

	@Autowired
	private UserMapper userMapper;

	@RequestMapping("hello")
	@NeedLogin
	public String hello() {
		PropertyUtil.writeProperty("testKey", "testValue");
		return "hello world " + PropertyUtil.getProperty("testKey");
	}

	@RequestMapping("user")
	public User user() {
		return userMapper.findOneUser("1");
	}

	@RequestMapping("userList")
	public Page userList() {
		PageHelper.startPage(1, 3);
		Page<User> list = (Page<User>) userMapper.findAllUser();

//		return "Total:" + list.getTotal() + ",PageNum:" + list.getPageNum() + ",CountColumn:" + list.getCountColumn()
//				+ ",Pages:" + list.getPages() + ",PageSize:" + list.getPageSize();
		return list;
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

}
