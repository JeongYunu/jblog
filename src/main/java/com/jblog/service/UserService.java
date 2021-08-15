package com.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jblog.dao.UserDao;
import com.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public boolean idCheck(String id) {
		System.out.println("[UserService.idCheck]");
		UserVo userVo = userDao.idCheck(id);
		if(userVo == null) {
			return true;
		}else {
			return false;
		}
	}
	
	public int insertUser(UserVo userVo) {
		System.out.println("[UserService.insertUser]");
		int count = userDao.insertUser(userVo);
		
		return count;
	}
	
	public UserVo selectUserInfo(UserVo userVo) {
		System.out.println("[UserService.selectUserInfo]");
		UserVo userInfo = userDao.selectUserInfo(userVo);
				
		return userInfo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
