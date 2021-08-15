package com.jblog.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jblog.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserVo idCheck(String id) {
		System.out.println("[UserDao.idCheck]");

		return sqlSession.selectOne("user.idCheck", id);
	}
	
	public int insertUser(UserVo userVo) {
		System.out.println("[UserDao.insertUser]");
		
		return sqlSession.insert("user.insertUser", userVo);
	}
	
	public UserVo selectUserInfo(UserVo userVo) {
		System.out.println("[UserDao.selectUserInfo]");
		
		return sqlSession.selectOne("user.selectUserInfo", userVo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
