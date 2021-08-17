package com.jblog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jblog.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int insertPost(PostVo postVo) {
		System.out.println("[PostDao.insertPost]");
		
		return sqlSession.insert("post.insertPost", postVo);
	}
	
	public List<PostVo> getPostTitle(){
		System.out.println("[PostDao.getPostTitle]");
		
		return sqlSession.selectList("post.getPostTitle");
	}
}
