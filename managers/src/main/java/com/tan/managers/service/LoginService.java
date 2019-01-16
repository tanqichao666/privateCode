package com.tan.managers.service;

import java.util.Map;

import com.tan.managers.model.UserInfo;

public interface LoginService {

	UserInfo findByName(String name);

	void insertUser(UserInfo user);

	void insertRole(UserInfo user);

	public UserInfo getOne (String id);

	Map<String, Object> createToken(String id) throws Exception;
}
