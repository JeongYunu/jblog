package com.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jblog.dao.PostDao;
import com.jblog.vo.PostVo;

@Service
public class PostService {

	@Autowired
	private PostDao postDao;
	
	public int insertPost(PostVo postVo) {
		System.out.println("[PostService.insertPost]");
		int count = postDao.insertPost(postVo);
		
		return count;
	}
	
	public List<PostVo> getPostTitle(){
		System.out.println("[PostService.getPostName");
		List<PostVo> postVo = postDao.getPostTitle();
		
		return postVo;
	}
	
	
	
	
}
