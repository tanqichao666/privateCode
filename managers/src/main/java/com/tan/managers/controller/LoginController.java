package com.tan.managers.controller;

import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tan.managers.model.ResponseObject;
import com.tan.managers.model.Result;
import com.tan.managers.model.UserInfo;
import com.tan.managers.service.LoginService;

@RestController
public class LoginController {
	private final static Logger log = LoggerFactory.getLogger("LoginContorller.class");
	@Autowired
	private LoginService loginService ;
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseObject<Object> hello(String username,String password) throws Exception {
		ResponseObject<Object> responseObject = new ResponseObject<>();

		//用户信息
		UserInfo user = loginService.findByName(username);

		//账号不存在
		if(user == null) {
			responseObject.setCode("401");
			responseObject.setMessage("账号不存在");
			return responseObject;
		}

		//密码错误
		if(!user.getPassword().equals(DigestUtils.md5Hex(password))) {
			responseObject.setCode("401");
			responseObject.setMessage("密码不正确");
			return responseObject;
		}

		//生成token，并保存到数据库
		Map<String, Object> result=loginService.createToken(user.getId());
		responseObject.setCode("200");
		responseObject.setMessage("ok");
		responseObject.setData(result);
		log.info("==========================="+responseObject);
		return responseObject;
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ResponseObject addUser(UserInfo user) {
		ResponseObject responseObject = new ResponseObject();
		loginService.insertUser(user);
		responseObject.setMessage("66666");
		log.info("==========================="+responseObject);
		return responseObject;
	}
	
	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	public ResponseObject addRole(UserInfo user) {
		ResponseObject responseObject = new ResponseObject();
		loginService.insertRole(user);
		responseObject.setMessage("66666");
		log.info("==========================="+responseObject);
		return responseObject;
	}
	
	@RequestMapping(value = "/findById", method = RequestMethod.GET)
	public ResponseObject<UserInfo> findById(String id) {
		ResponseObject<UserInfo> responseObject = new ResponseObject<>();
		UserInfo one = loginService.getOne(id);
		responseObject.setData(one);
		responseObject.setMessage("66666");
		log.info("==========================="+responseObject);
		return responseObject;
	}
	
}
