package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.service.AdminService;
import com.douzone.mysite.vo.AdminVO;

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
	
}
