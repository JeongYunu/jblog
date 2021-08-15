package com.jblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jblog.service.UserService;
import com.jblog.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm(){
		System.out.println("[UserController.joinForm]");
		
		return "user/joinForm";
	}
	
	@ResponseBody
	@RequestMapping(value="/idCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean idCheck(@RequestParam("id") String id) {
		System.out.println("[UserController.idCheck]");
		boolean idCheck = userService.idCheck(id);
		
		return idCheck;
	}

	@RequestMapping(value="/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinSuccess(@ModelAttribute UserVo userVo) {
		System.out.println("[UserController.joinSuccess]");
		userService.insertUser(userVo);
		
		return "user/joinSuccess";
	}
	
	@RequestMapping(value="/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("[UserController.loginForm]");
		
		return "user/loginForm";
	}
	
	@RequestMapping(value="/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController.login]");
		UserVo authUser = userService.selectUserInfo(userVo);
		if(authUser != null) {		
			System.out.println("[로그인성공]");
			session.setAttribute("authUser", authUser);
			return "redirect:/";
		}else {		
			System.out.println("[로그인실패]");
			return "redirect:/user/loginForm?result=fail";
		}
	}
	
	@RequestMapping(value="/logOut", method = { RequestMethod.GET, RequestMethod.POST })
	public String logOut(HttpSession session) {
		System.out.println("[UserController.logOut]");
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}



