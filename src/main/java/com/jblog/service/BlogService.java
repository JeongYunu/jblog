package com.jblog.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jblog.dao.BlogDao;
import com.jblog.vo.BlogVo;
import com.jblog.vo.UserVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;

	public UserVo adminUser(String id) {
		System.out.println("[BlogService.adminUser]");
		UserVo userVo = blogDao.adminUser(id);

		return userVo;
	}

	public BlogVo getBlogInfo(String id) {
		System.out.println("[BlogService.getBlogInfo]");
		BlogVo blogVo = blogDao.getBlogInfo(id);

		return blogVo;
	}

	public int updateUserBlog(String id, String title, MultipartFile file) {
		System.out.println("[BlogService.updateUserBlog]");
		if("".equals(title)) {
			String saveDir = "/Users/yunu/Desktop/javaStudy/upload";
			String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
			String filePath = saveDir + "/" + saveName;
			try {
				byte[] fileData = file.getBytes();
				OutputStream out = new FileOutputStream(filePath);
				BufferedOutputStream bOut = new BufferedOutputStream(out);

				bOut.write(fileData);
				bOut.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int count = blogDao.updateUserBlogWithFile(id, saveName);
			return count;
		}else if("".equals(file.getOriginalFilename())) {
			int count = blogDao.updateUserBlogWithTitle(id, title);
			return count;
		}else {
			String saveDir = "/Users/yunu/Desktop/javaStudy/upload";
			String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
			String filePath = saveDir + "/" + saveName;
			try {
				byte[] fileData = file.getBytes();
				OutputStream out = new FileOutputStream(filePath);
				BufferedOutputStream bOut = new BufferedOutputStream(out);
	
				bOut.write(fileData);
				bOut.close();
	
			} catch (IOException e) {
				e.printStackTrace();
			}

			int count = blogDao.updateUserBlog(id, title, saveName);
			return count;
		}

	}
	
	public int insertUserBlogIfo(String id, String title, MultipartFile file) {
		System.out.println("[BlogService.insertUserBlogIfo]");
		if("".equals(title)) {
			String saveDir = "/Users/yunu/Desktop/javaStudy/upload";
			String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
			String filePath = saveDir + "/" + saveName;
			try {
				byte[] fileData = file.getBytes();
				OutputStream out = new FileOutputStream(filePath);
				BufferedOutputStream bOut = new BufferedOutputStream(out);

				bOut.write(fileData);
				bOut.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int count = blogDao.insertUserBlogWithFile(id, saveName);
			return count;
		}else if("".equals(file.getOriginalFilename())) {
			int count = blogDao.insertUserBlogWithTitle(id, title);
			return count;
		}else {
			String saveDir = "/Users/yunu/Desktop/javaStudy/upload";
			String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
			String filePath = saveDir + "/" + saveName;
			try {
				byte[] fileData = file.getBytes();
				OutputStream out = new FileOutputStream(filePath);
				BufferedOutputStream bOut = new BufferedOutputStream(out);
	
				bOut.write(fileData);
				bOut.close();
	
			} catch (IOException e) {
				e.printStackTrace();
			}

			int count = blogDao.insertUserBlog(id, title, saveName);
			return count;
		}

	}
}
