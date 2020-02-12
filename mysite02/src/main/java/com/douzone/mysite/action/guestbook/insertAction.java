package com.douzone.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVO;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class insertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
		String password = request.getParameter("pass");
		String contents = request.getParameter("content");
		 
		
		if(!name.isEmpty() || !password.isEmpty() || !contents.isEmpty()){
			GuestbookVO vo = new GuestbookVO();
		
			vo.setName(name);
			vo.setPassword(password);
			vo.setContents(contents);
			
			
			new GuestbookRepository().insert(vo);	
		}
		
		WebUtil.redirect(request.getContextPath()+"/guestbook",request,response);


	}

}
