package com.jblog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<CategoryVo> getCategoryList(){
		System.out.println("[CategoryDao.getCategoryList]");
		
		return sqlSession.selectList("category.getCategoryList");
	}
	
	public int addCategoryKey(CategoryVo categoryVo) {
		System.out.println("[CategoryDao.addCategory]");
		
		return sqlSession.insert("category.addCategoryKey", categoryVo);
	}
	
	public CategoryVo selectListOne(int cateNo) {
		System.out.println("[CategoryDao.selectListOne]");

		return sqlSession.selectOne("category.selectListOne", cateNo);
	}
	
	public int deleteCategoryList(int no) {
		System.out.println("[CategoryDao.deleteCategoryList]");
		
		return	sqlSession.delete("category.deleteCategoryList", no);
	}
}
