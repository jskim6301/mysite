package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.douzone.mysite.vo.GuestbookVO;

@Repository
public class GuestbookRepository {
	
	@Autowired
	private SqlSession sqlSession;
	


	public int insert(GuestbookVO guestbookVO) {
		return sqlSession.insert("guestbook.insert",guestbookVO); 
	}
	
	
	public List<GuestbookVO> findAll(){
//		StopWatch sw = new StopWatch();
//		sw.start();
		
		List<GuestbookVO> list = sqlSession.selectList("guestbook.findAll");
		
//		sw.stop();
//		Long totalTime = sw.getTotalTimeMillis();
//		System.out.println(totalTime);
		return list;
	}
	
	public int delete(Long no, String password) {
		Map<String,Object> map = new HashMap<>();
		map.put("no",no);
		map.put("password",password);
		return sqlSession.delete("guestbook.delete",map);
	}


	public List<GuestbookVO> findAll(Long startNo) {
		return sqlSession.selectList("guestbook.findAllByNo",startNo);
	}
	
}
