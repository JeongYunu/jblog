package com.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jblog.dao.CategoryDao;
import com.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	public List<CategoryVo> getCategoryList(){
		System.out.println("[CategoryService.getCategoryList]");
		List<CategoryVo> categoryList = categoryDao.getCategoryList();
		System.out.println("service=" + categoryList);
		
		return categoryList;
	}

	public CategoryVo addCategory(CategoryVo categoryVo) {
		System.out.println("[CategoryService.addCategory]");
		categoryDao.addCategoryKey(categoryVo);
		int cateNo = categoryVo.getCateNo();
		System.out.println(cateNo);
		CategoryVo categoryListOne = categoryDao.selectListOne(cateNo);
		System.out.println("service=" + categoryListOne);
		
		return categoryListOne;
	}
	
	public int deleteCategoryList(int cateNo) {
		System.out.println("[CategoryService.deleteCategoryList]");
		int count = categoryDao.deleteCategoryList(cateNo);
		
		return count;
	}
	
	public List<CategoryVo> getCategoryName(){
		System.out.println("[CategoryService.getCategoryName]");
		List<CategoryVo> categoryName = categoryDao.getCategoryList();
		
		return categoryName;
	}
}
