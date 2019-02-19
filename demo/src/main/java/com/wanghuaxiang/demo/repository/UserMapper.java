package com.wanghuaxiang.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wanghuaxiang.demo.bean.User;

/**
 * @author: 王华翔
 * @version: 创建时间：2019年1月1日 下午10:19:04
 * @description:
 */
@Mapper
public interface UserMapper {
	User findOneUser(@Param("userId") String userId);

	List<User> findAllUser();
}
