package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.AdminVO;

@Repository
public class AdminRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public int update(AdminVO adminVO) {
		return sqlSession.update("admin.update",adminVO);
	}

	public AdminVO findAll() {
		return sqlSession.selectOne("admin.select");
	}
	

}
