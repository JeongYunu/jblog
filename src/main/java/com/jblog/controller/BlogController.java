package com.jblog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jblog.service.BlogService;
import com.jblog.vo.BlogVo;
import com.jblog.vo.UserVo;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value="/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String blogMain(@PathVariable("id") String id, Model model) {
		System.out.println("[BlogController.blogMain]");
		UserVo userVo = blogService.adminUser(id);
		BlogVo blogVo = blogService.getBlogInfo(id);
		Map<String, Object> blogAdmin = new HashMap<>();
		blogAdmin.put("adminUser", userVo);
		blogAdmin.put("blogInfo", blogVo);
		
		model.addAttribute("blogAdmin", blogAdmin);
		if(userVo != null) {
			return "blog/blog-main";
		}else {
			return "error/403";
		}
	}
	
	@RequestMapping(value="/{id}/admin/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public String admin(@PathVariable("id") String id, HttpSession session, Model model) {
		System.out.println("[BlogController.admin]");
		UserVo userVo = blogService.adminUser(id);
		BlogVo blogVo = blogService.getBlogInfo(id);
		Map<String, Object> blogAdmin = new HashMap<>();
		blogAdmin.put("adminUser", userVo);
		blogAdmin.put("blogInfo", blogVo);
		System.out.println("여기냐" + blogVo);
		
		model.addAttribute("blogAdmin", blogAdmin);
		UserVo loginUser = ((UserVo)session.getAttribute("authUser"));
		if(loginUser == null) {
			return "error/403";
		}else if(loginUser.getId().equals(id)){
			return "blog/admin/blog-admin-basic";
		}else {
			return "error/403";
		}
	}
	
	@RequestMapping(value="/{id}/admin/editBlog", method = { RequestMethod.GET, RequestMethod.POST })
	public String editBlog(@PathVariable("id") String id, HttpSession session,
			@RequestParam(value="blogTitle", required=false, defaultValue="") String title,
			@RequestParam(value="file", required=false, defaultValue = "") MultipartFile file) {
		System.out.println("[BlogController.editBlog]");
		BlogVo blogVo = blogService.getBlogInfo(id);
		if(blogVo == null) {
			blogService.insertUserBlogIfo(id, title, file);
		}else {
			blogService.updateUserBlog(id, title, file);
			
		}
		
		UserVo loginUser = ((UserVo)session.getAttribute("authUser"));
		if(loginUser == null) {
			return "error/403";
		}else if(loginUser.getId().equals(id)){
			return "redirect:/" + id + "/admin/basic";
		}else {
			return "error/403";
		}
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
