package com.tan.managers.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tan.managers.model.Role;
import com.tan.managers.model.SysUserToken;
import com.tan.managers.model.UserInfo;

@Mapper

public interface UserDao {

	public UserInfo findByName(@Param("name")String name);

	public void insertUser(UserInfo user);

	public void insertRole(UserInfo user);

	public void inserUserRole(String userId, List<Role> roles);

	public UserInfo getOne(String id);

	public SysUserToken queryByUserId(String id);

	public void save(@Param("token")SysUserToken token);

	public void update(@Param("token")SysUserToken token);
	
}
