package com.tan.managers.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tan.managers.config.TokenGenerator;
import com.tan.managers.dao.UserDao;
import com.tan.managers.model.Role;
import com.tan.managers.model.SysUserToken;
import com.tan.managers.model.UserInfo;
import com.tan.managers.service.LoginService;
import com.tan.managers.utils.UUIDUtils;
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserDao userDao;
	@Override
	public UserInfo findByName(String name) {
		return userDao.findByName(name);
	}
	private final static int EXPIRE = 30*60;
	@Override
	public void insertUser(UserInfo user) {
		// 设置id
		user.setId(UUIDUtils.getUUID());
		
		// 密码加密
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		
		userDao.insertUser(user);
	}

	@Override
	public void insertRole(UserInfo user) {
//		String userId = user.getId();
		List<Role> roles = user.getRoles();
		if(CollectionUtils.isNotEmpty(roles)) {
			roles.stream().forEach(action ->{
				action.setId(UUIDUtils.getUUID());
			});
			userDao.insertRole(user);
//			userDao.inserUserRole(userId,roles);
		}
	}
	
	public UserInfo getOne (String id) {
		return userDao.getOne (id);
	}

	@Override
	public Map<String, Object> createToken(String id) throws Exception {
		//生成一个token
		String token = TokenGenerator.generateValue();

		//当前时间
		Date now = new Date();
		//过期时间
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		//判断是否生成过token
		SysUserToken tokenEntity = userDao.queryByUserId(id);
		if(tokenEntity == null){
			tokenEntity = new SysUserToken();
			tokenEntity.setUserId(id);
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			//保存token
			save(tokenEntity);
		}else{
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			//更新token
			update(tokenEntity);
		}

		Map<String, Object> result = new HashMap<>();
		result.put("token", token);
		result.put("expire", EXPIRE);
		return result;
	}

	@Transactional
	private void save(SysUserToken token){
		userDao.save(token);
//		sysUserTokenRedis.saveOrUpdate(token);
	}

	@Transactional
	private void update(SysUserToken token){
//		sysUserTokenRedis.delete(token);
		userDao.update(token);
	}
}
