package com.jblog.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jblog.vo.BlogVo;
import com.jblog.vo.UserVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public Map<String, Object> getMainPostInfo(){
		System.out.println("[BlogDao.getMainPostInfo]");
		
		return sqlSession.selectOne("blog.mainPostInfo");
	}
	
	public UserVo adminUser(String id) {
		System.out.println("[BlogDao.adminUser]");
		
		return sqlSession.selectOne("blog.adminUser", id);
	}
	
	public BlogVo getBlogInfo(String id) {
		System.out.println("[BlogDao.getBlogInfo]");
		
		return sqlSession.selectOne("blog.getBlogInfo", id);
	}
	
	public int updateUserBlog(String id, String title, String saveName) {
		System.out.println("[BlogDao.updateUserBlog]");
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("blogTitle", title);
		map.put("logoFile", saveName);
		
		return sqlSession.update("blog.updateUserBlog", map);
	}
	
	
	public int updateUserBlogWithTitle(String id, String blogTitle) {
		System.out.println("[BlogDao.updateUserBlogWithTitle]");
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("blogTitle", blogTitle);
		
		return sqlSession.update("blog.updateUserBlogWithTitle", map);
	}
	
	
	
	
	
	
	
	
	
}
