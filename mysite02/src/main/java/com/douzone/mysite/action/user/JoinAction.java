package com.douzone.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVO;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		UserVO userVO = new UserVO();
		userVO.setName(name);
		userVO.setEmail(email);
		userVO.setPassword(password);
		userVO.setGender(gender);
		
		
		new UserRepository().insert(userVO);
		
		WebUtil.redirect(request.getContextPath()+"/user?a=joinsuccess", request, response);
		
	}

}
