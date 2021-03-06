package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVO;

public class BoardRepository {
	private Connection getConnection() throws SQLException {
		Connection con = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			

			String url = "jdbc:mysql://192.168.1.115:3307/webdb";
			con =  DriverManager.getConnection(url,"webdb","webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:"+ e);
		}
		return con;
	}
	
	public Boolean initInsert(BoardVO boardVO) {
			
			Boolean result = false;
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = getConnection();
				//INSERT INTO board VALUES(NULL,'ㅎㅇ','ㅎㅇㅎㅇ',4,NOW(),(SELECT IFNULL(MAX(b.g_no) + 1,0) FROM board b),1,0,1)
				String sql = "insert into board values(null,?,?,0,now(),(SELECT IFNULL(MAX(b.g_no) + 1,0) FROM board b),1,0,?)";
				pstmt = con.prepareStatement(sql);
	
				pstmt.setString(1, boardVO.getTitle());
				pstmt.setString(2, boardVO.getContents());
				pstmt.setLong(3, boardVO.getUserNo());
	
				
				int count = pstmt.executeUpdate();
				
				result = count == 1;
				
			}catch (SQLException e) {
				System.out.println("error" + e);
			}finally {
				try {
					if(pstmt != null) {
						pstmt.close();
					}
					if(con != null) {
						con.close();	
					}				
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
	}

	public List<BoardVO> findAll(int displayPost,int postNum){
		List<BoardVO> result = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = getConnection();
			//select a.no,a.title,a.contents,a.Hit,a.reg_date,a.g_no,a.o_no,a.depth,a.user_no,b.name from board a,user b where a.user_no = b.no order by a.no desc;
			String sql = "select a.no,a.title,a.contents,a.hit,a.reg_date,a.g_no,a.o_no,a.depth,a.user_no,b.name from board a,user b where a.user_no = b.no order by a.g_no desc, a.o_no desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, displayPost);
			pstmt.setInt(2, postNum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				Integer hit = rs.getInt(4);
				String regDate = rs.getString(5);
				Integer gNo = rs.getInt(6);
				Integer oNo = rs.getInt(7);
				Integer depth = rs.getInt(8);
				Long userNo = rs.getLong(9);
				String userName = rs.getString(10);
				
				
				BoardVO vo = new BoardVO();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setgNo(gNo);
				vo.setoNo(oNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setUserName(userName);
				
				result.add(vo);
			}			
			
		}catch (SQLException e) {
			System.out.println("error" + e);
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();	
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		return result;
	}
	
	public List<BoardVO> findAll2(){
		List<BoardVO> result = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = getConnection();
			//select a.no,a.title,a.contents,a.Hit,a.reg_date,a.g_no,a.o_no,a.depth,a.user_no,b.name from board a,user b where a.user_no = b.no order by a.no desc;
			String sql = "select a.no,a.title,a.contents,a.hit,a.reg_date,a.g_no,a.o_no,a.depth,a.user_no,b.name from board a,user b where a.user_no = b.no order by a.g_no desc, a.o_no desc";
			pstmt = con.prepareStatement(sql);

			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				Integer hit = rs.getInt(4);
				String regDate = rs.getString(5);
				Integer gNo = rs.getInt(6);
				Integer oNo = rs.getInt(7);
				Integer depth = rs.getInt(8);
				Long userNo = rs.getLong(9);
				String userName = rs.getString(10);
				
				
				BoardVO vo = new BoardVO();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setgNo(gNo);
				vo.setoNo(oNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setUserName(userName);
				
				result.add(vo);
			}			
			
		}catch (SQLException e) {
			System.out.println("error" + e);
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();	
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		return result;
	}	

	public BoardVO findByNo(Long no) {
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO boardVO = new BoardVO();
		try {
			con = getConnection();
			
			
			// select a.no,b.name,a.title,a.contents from board a, user b where a,user_no = b.no where a.no = ?
			String sql = "select a.no,b.name,a.title,a.contents,a.hit,a.reg_date,a.g_no,a.o_no,a.depth from board a, user b where a.user_no = b.no and a.no = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Long newNo = rs.getLong(1);
				String userName = rs.getString(2);
				String title = rs.getString(3);
				String contents = rs.getString(4);
				Integer hit = rs.getInt(5);
				String regDate = rs.getString(6);
				Integer gNo = rs.getInt(7);
				Integer oNo = rs.getInt(8);
				Integer depth = rs.getInt(9);
				
				boardVO.setNo(newNo);
				boardVO.setUserName(userName);
				boardVO.setTitle(title);
				boardVO.setContents(contents);
				boardVO.setHit(hit);
				boardVO.setRegDate(regDate);
				boardVO.setgNo(gNo);
				boardVO.setoNo(oNo);
				boardVO.setDepth(depth);
				
			}		

			
		}catch (SQLException e) {
			System.out.println("error" + e);
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();	
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return boardVO;		
	}
	
	public void update(BoardVO boardVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();

			String sql = "update board set title =?, contents=?, reg_date = now() where no = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1,boardVO.getTitle());
			pstmt.setString(2,boardVO.getContents());
			pstmt.setLong(3, boardVO.getNo());
			
			pstmt.executeUpdate();			
			
		}catch (SQLException e) {
			System.out.println("error" + e);
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();	
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	public Boolean insert(BoardVO boardVO) {

		
		Boolean result = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			//INSERT INTO board VALUES(NULL,'ㅎㅇ','ㅎㅇㅎㅇ',4,NOW(),(SELECT IFNULL(MAX(b.g_no) + 1,0) FROM board b),1,0,1)
			String sql = "insert into board values(null,?,?,0,now(),?,?,?+1,?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, boardVO.getTitle());
			pstmt.setString(2, boardVO.getContents());
			pstmt.setInt(3,boardVO.getgNo());//해당번호 그대로
			pstmt.setInt(4,boardVO.getoNo());
			pstmt.setInt(5,boardVO.getDepth());			
			pstmt.setLong(6,boardVO.getUserNo());

			
			int count = pstmt.executeUpdate();
			
			result = count == 1;
			
		}catch (SQLException e) {
			System.out.println("error" + e);
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();	
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
		
	}

	public void updateSequece(BoardVO boardVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();

			String sql = "update board set o_no = o_no+1 where g_no = ? and o_no >= ?";
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setInt(1,boardVO.getgNo());
			pstmt.setInt(2,boardVO.getoNo());
			
			
			
			
			pstmt.executeUpdate();			
			
		}catch (SQLException e) {
			System.out.println("error" + e);
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();	
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
	}

	public void updateViewCnt(BoardVO boardVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();

			String sql = "update board set hit = hit+1 where no = ? ";
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setLong(1,boardVO.getNo());		
			
			pstmt.executeUpdate();			
			
		}catch (SQLException e) {
			System.out.println("error" + e);
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();	
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
	}

	public void delete(BoardVO boardVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();

			String sql = "update board set title = '작성자에 의해 삭제되었습니다.',contents=''  where no = ? and user_no = ?";
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setLong(1,boardVO.getNo());
			pstmt.setLong(2,boardVO.getUserNo());			
			
			pstmt.executeUpdate();			
			
		}catch (SQLException e) {
			System.out.println("error" + e);
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();	
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
	}

	public int count() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		try {
			con = getConnection();

			String sql = "select count(*) from board";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);				
			}
		}catch (SQLException e) {
			System.out.println("error" + e);
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();	
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
				
		return count;
	}

	public List<BoardVO> findByTitle(String kwd,int displayPost,int postNum) {
		List<BoardVO> result = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			
			// 
			String sql = "select a.no,b.name,a.title,a.contents,a.hit,a.reg_date from board a, user b where a.user_no = b.no and a.title like concat('%',?,'%') limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, kwd);
			pstmt.setInt(2, displayPost);
			pstmt.setInt(3, postNum);			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String userName = rs.getString(2);
				String title = rs.getString(3);
				String contents = rs.getString(4);
				Integer hit = rs.getInt(5);
				String regDate = rs.getString(6);
				
				
				BoardVO boardVO = new BoardVO();
				boardVO.setNo(no);
				boardVO.setUserName(userName);
				boardVO.setTitle(title);
				boardVO.setContents(contents);
				boardVO.setHit(hit);
				boardVO.setRegDate(regDate);
				
				
				result.add(boardVO);
			}		

			
		}catch (SQLException e) {
			System.out.println("error" + e);
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();	
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;				
	}

	public List<BoardVO> findByName(String kwd,int displayPost,int postNum) {
		
		List<BoardVO> result = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			
			// select a.no,b.name,a.title,a.contents from board a, user b where a,user_no = b.no where a.no = ?
			String sql = "select a.no,b.name,a.title,a.contents,a.hit,a.reg_date from board a, user b where a.user_no = b.no and b.name like concat('%',?,'%') limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, kwd);
			pstmt.setInt(2, displayPost);
			pstmt.setInt(3, postNum);			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String userName = rs.getString(2);
				String title = rs.getString(3);
				String contents = rs.getString(4);
				Integer hit = rs.getInt(5);
				String regDate = rs.getString(6);
						
				
				BoardVO boardVO = new BoardVO();
				boardVO.setNo(no);
				boardVO.setUserName(userName);
				boardVO.setTitle(title);
				boardVO.setContents(contents);
				boardVO.setHit(hit);
				boardVO.setRegDate(regDate);
				
				
				result.add(boardVO);
			}		

			
		}catch (SQLException e) {
			System.out.println("error" + e);
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();	
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;	
	}

	public int countTitle(String kwd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		try {
			con = getConnection();

			String sql = "select count(*) from board a, user b where a.user_no = b.no and a.title like concat('%',?,'%')";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, kwd);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);				
			}
		}catch (SQLException e) {
			System.out.println("error" + e);
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();	
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
				
		return count;

	}

	public int countName(String kwd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		try {
			con = getConnection();

			String sql = "select count(*) from board a, user b where a.user_no = b.no and b.name like concat('%',?,'%')";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, kwd);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);				
			}
		}catch (SQLException e) {
			System.out.println("error" + e);
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();	
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return count;
	}
}


	
