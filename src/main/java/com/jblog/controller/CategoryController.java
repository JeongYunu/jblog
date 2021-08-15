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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jblog.service.BlogService;
import com.jblog.service.CategoryService;
import com.jblog.vo.BlogVo;
import com.jblog.vo.CategoryVo;
import com.jblog.vo.UserVo;

@Controller
public class CategoryController {
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	

	@RequestMapping(value="/{id}/admin/category", method = { RequestMethod.GET, RequestMethod.POST })
	public String category(@PathVariable("id") String id, HttpSession session, Model model) {
		System.out.println("[CategoryController.category]");
		Map<String, Object> blogAdmin = new HashMap<>();
		UserVo userVo = blogService.adminUser(id);
		BlogVo blogVo = blogService.getBlogInfo(id);
		blogAdmin.put("adminUser", userVo);
		blogAdmin.put("blogInfo", blogVo);
		System.out.println(blogVo);
		
		model.addAttribute("blogAdmin", blogAdmin);
		
		UserVo loginUser = ((UserVo)session.getAttribute("authUser"));
		if(loginUser == null) {
			return "error/403";
		}else if(loginUser.getId().equals(id)){
			return "blog/admin/blog-admin-cate";
		}else {
			return "error/403";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}/admin/getCategory", method = { RequestMethod.GET, RequestMethod.POST })
	public List<CategoryVo> categoryList(){
		System.out.println("[Categorycontroller.categoryList]");
		List<CategoryVo> categoryList = categoryService.getCategoryList();
		System.out.println(categoryList);
		
		return categoryList;
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}/admin/addCategory", method = { RequestMethod.GET, RequestMethod.POST })
	public CategoryVo addCategory(@ModelAttribute CategoryVo categoryVo) {
		System.out.println("[CategoryController.addCategory]");
		CategoryVo categoryList  = categoryService.addCategory(categoryVo);
		
		return categoryList;
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}/admin/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public int delete(@RequestParam("dbNo") int no) {
		System.out.println("[CategoryController.delete]");
		int count = categoryService.deleteCategoryList(no);
		System.out.println(count + "건 삭제");
		
		return count;
	}
	
	
	
	
	
	
	
	
}
