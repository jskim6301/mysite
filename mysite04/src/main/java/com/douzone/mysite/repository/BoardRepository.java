package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVO;

@Repository
public class BoardRepository {
//	@Autowired
//	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;

	public List<BoardVO> findAll(int displayPost,int postNum,String keyword){
		Map<String,Object> map = new HashMap<>();
		System.out.println("displayPost"+displayPost);
		System.out.println("postNum"+postNum);
		map.put("displayPost",displayPost);
		map.put("postNum",postNum);
		map.put("keyword",keyword);
		List<BoardVO> list = sqlSession.selectList("board.findAll",map);
		return list;
	}
	

	public BoardVO findByNo(Long no) {
		return sqlSession.selectOne("board.findByNo",no);	
	}
	public BoardVO findByNoAndUserNo( Long no, Long userNo ) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put( "no", no );
		map.put( "userNo", userNo );
		
		return sqlSession.selectOne( "board.findByNoAndUserNo", map );
	}	
	public int update(BoardVO boardVO) {		
		return sqlSession.update( "board.update", boardVO );
	}

	public int insert(BoardVO boardVO) {

		return sqlSession.insert("board.insert",boardVO);
	}

	public int updateSequece(Integer gNo,Integer oNo) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("gNo",gNo);
		map.put("oNo",oNo);
		return sqlSession.update("board.updateOrderNo",map);
	}

	public int updateHit(Long no) {
		return sqlSession.update("board.updateHit",no);
		
	}

	public int delete( Long no, Long userNo) {

		Map<String, Long> map = new HashMap<String, Long>();
		map.put( "no", no );
		map.put( "userNo", userNo );
		
		return sqlSession.delete( "board.delete", map );
		
	}

	public int getTotalCount(String keyword) {
		return sqlSession.selectOne("board.totalCount",keyword);
	}

	


}
