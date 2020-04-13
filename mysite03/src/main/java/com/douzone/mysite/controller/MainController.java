package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.service.AdminService;
import com.douzone.mysite.vo.AdminVO;
import com.douzone.mysite.vo.UserVO;

@Controller
public class MainController {
	
 
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping({"","/main"})
	public String index(Model model) {
		
		AdminVO adminVO = adminService.getContents();
		model.addAttribute("adminVO",adminVO);
		
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/hello")
	public UserVO hello() {
		return new UserVO();
	}
	
}
