package com.douzone.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.UserService;

@Controller("apiUserController")
@RequestMapping("/api/user")
public class UserController { //UserController (빈)이 2개가 있어서 오류  =>@Controller("apiUserController") id 설정  , 기본적으로 UserController 를 userController로 만듬(앞에 소문자로)
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value="/checkemail",method=RequestMethod.GET)
	public JsonResult checkEmail(@RequestParam(value="email", required=true , defaultValue="") String email) {
		
		boolean exist = userService.existUser(email);
		
		/*
		 * Map<String,Object> map = new HashMap<>(); 
		 * map.put("result", exist ? "exist" : "not exist");
		 * return map;
		 */
		
		return JsonResult.success(exist);
	}
}
