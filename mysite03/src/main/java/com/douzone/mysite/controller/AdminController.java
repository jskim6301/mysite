package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.AdminService;
import com.douzone.mysite.vo.AdminVO;
import com.douzone.security.Auth;

@Auth("ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("")
	public String main(Model model) {
		AdminVO adminVO = adminService.getContents();
		model.addAttribute("adminVO",adminVO);
		return "admin/main";
	}
	@RequestMapping(value="/main/update",method=RequestMethod.POST)
	public String main(
			@ModelAttribute AdminVO adminVO,Model model) {	
		System.out.println("adminVO.getMultipartFile() "+adminVO.getMultipartFile());
		System.out.println("adminVO.getTitle() "+adminVO.getTitle());
		System.out.println("adminVO.getWelcomeMessage() "+adminVO.getWelcomeMessage());
		System.out.println("adminVO.getDescription() "+adminVO.getDescription());
		
		String url = adminService.restore(adminVO.getMultipartFile());
		adminVO.setProfile(url);
		adminService.modifyContents(adminVO);
		

		return "admin/main";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
}
