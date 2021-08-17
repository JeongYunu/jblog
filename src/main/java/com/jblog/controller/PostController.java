package com.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jblog.service.BlogService;
import com.jblog.service.CategoryService;
import com.jblog.service.PostService;
import com.jblog.vo.BlogVo;
import com.jblog.vo.CategoryVo;
import com.jblog.vo.PostVo;
import com.jblog.vo.UserVo;

@Controller
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/{id}/admin/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm(@PathVariable("id") String id, Model model, HttpSession session) {
		System.out.println("[PostController.writeForm]");
		Map<String, Object> blogAdmin = new HashMap<>();
		UserVo userVo = blogService.adminUser(id);
		BlogVo blogVo = blogService.getBlogInfo(id);
		blogAdmin.put("adminUser", userVo);
		blogAdmin.put("blogInfo", blogVo);
		
		List<CategoryVo> categoryList = categoryService.getCategoryList();
		blogAdmin.put("categoryList", categoryList);
		
		model.addAttribute("blogAdmin", blogAdmin);
		
		UserVo loginUser = ((UserVo)session.getAttribute("authUser"));
		if(loginUser == null) {
			return "error/403";
		}else if(loginUser.getId().equals(id)){
			return "blog/admin/blog-admin-write";
		}else {
			return "error/403";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}/admin/write", method = { RequestMethod.GET, RequestMethod.POST })
	public int write(@PathVariable("id") String id, @ModelAttribute PostVo postVo) {
		System.out.println("[PostController.write]");
		System.out.println(postVo);
		int count = postService.insertPost(postVo);
		
		return count;
	}
	
	
	
	
	
	
	
	
	
	
}
