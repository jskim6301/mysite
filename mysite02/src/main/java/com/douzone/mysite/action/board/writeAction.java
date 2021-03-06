package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVO;
import com.douzone.mysite.vo.UserVO;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class writeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//접근 제어(Access Control List, ACL)
		
		HttpSession session = request.getSession();// 
		if(session != null && session.getAttribute("authUser") != null) {
			
			BoardVO boardVO = new BoardVO();
			
			UserVO authUser = (UserVO)session.getAttribute("authUser");
			Long no =  authUser.getNo();
			
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			
			
			if(title.contains(" ")) { //공백이 포함되어 있으면 
				title = title.trim();  //양쪽 공백 제거 
				if(title.equals("")) { //제거를 해도 공백이라면
					title="";
				}
			}
			if(contents.contains(" ")) {
				contents = contents.trim();
				if(contents.equals("")) {
					contents="";
				}
			}
			
			if( request.getParameter("title").isEmpty() || request.getParameter("contents").isEmpty() || title.equals("") || contents.equals("") ) {
				WebUtil.redirect(request.getContextPath()+"/board?num=1", request, response);
				return;
			}
			

			
			
			
			// no값을 받으면..
			if(  !(request.getParameter("no").isEmpty()) ) { //no값이 빈값이 아니라면
				String pageNo1 = request.getParameter("no");// 페이지해당 번호
				Long pageNo2 = Long.parseLong(pageNo1);
				System.out.println(pageNo2+"받음");
				
				boardVO = new BoardRepository().findByNo(pageNo2);
				
				boardVO.setTitle(title);
				boardVO.setContents(contents);
				boardVO.setUserNo(no);
				
				new BoardRepository().updateSequece(boardVO);
				
				new BoardRepository().insert(boardVO);
				
				
				
			}else {//no이 빈값이 아니라면 처음 삽입
				boardVO.setTitle(title);
				boardVO.setContents(contents);
				boardVO.setUserNo(no);
				
				new BoardRepository().initInsert(boardVO);
			}
			WebUtil.redirect(request.getContextPath()+"/board?num=1", request, response);
			return;
		}
		WebUtil.redirect(request.getContextPath()+"/user?a=loginform", request, response);
		
		
		

	}

}


