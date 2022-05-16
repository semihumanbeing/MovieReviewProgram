package MovieDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import vo.MovieVO;
import vo.ReviewVO;
import vo.UserVO;
import vo.selectAllVO;

public class MovieDao {

	static MovieDao single = null;
	
	public static MovieDao getInstance() {
		
		if(single==null) {
			single = new MovieDao();
		}
		
		return single;
	}//static 초기화 

	
	private MovieDao() { //외부에서 내부변수에 접근할 수 없도록 기본생성자 생성
	}
	
	
	
	//TODO : 3개테이블 전체 조회(김다정)
	public List<selectAllVO> selectLAll_list(){ 
		
		List<selectAllVO> list = new ArrayList<selectAllVO>();
		
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet 	      rs    = null;
		
		String sql = "select * from selectAll";
		
		try {
			conn  = DBConnection.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			rs    = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int reviewIdx     = rs.getInt("reviewIdx");
				int remoIdx       = rs.getInt("remoIdx");
				int moIdx         = rs.getInt("moIdx");
				String reviewId   = rs.getString("reviewId");
				String userId     = rs.getString("userId");
				String movieTitle = rs.getString("movieTitle");
				
				selectAllVO vo = new selectAllVO();
						
				vo.setReviewIdx(reviewIdx);
				vo.setReviewId(reviewId);
				vo.setRemoIdx(remoIdx);
				vo.setUserId(userId);
				vo.setMoIdx(moIdx);
				vo.setMovieTitle(movieTitle);
				
				list.add(vo);
			}//while end
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}//try-catch end
		}
		return list;
	}//selectList() end
	
	
	
	//TODO : 영화조회(김다정)
	public List<MovieVO> select_movie_list(){ 
		
		List<MovieVO> list = new ArrayList<MovieVO>();
		
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet 	      rs    = null;
		
		String sql = "select * from movie";
		
		try {
			conn  = DBConnection.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			rs    = pstmt.executeQuery();
			
			while(rs.next()) {
			
				int    movieIdx   = rs.getInt("movieIdx");
				String movieTitle = rs.getString("movieTitle");
				
				MovieVO vo = new MovieVO();
				
				vo.setMovieIdx(movieIdx);
				vo.setMovieTitle(movieTitle);
				
				list.add(vo);
			}//while end
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}//try-catch end
		}
		return list;
	}//select_Movie_list() end
	
	
	
	
	
	//TODO : 전체리뷰조회(김다정)
	public List<ReviewVO> select_Review_All() {

		List<ReviewVO> list = new ArrayList<ReviewVO>();

		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet         rs = null;
		String sql = "select * from review order by reviewIdx";

		try {
			conn  = DBConnection.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			rs    = pstmt.executeQuery();

			while (rs.next()) {
				
				int    reviewIdx  = rs.getInt("reviewIdx");
				int    movieIdx   = rs.getInt("movieIdx");
				String id         = rs.getString("id");
				String reviewDate = rs.getString("reviewDate");
				String revewText  = rs.getString("reviewText");
				
				ReviewVO vo = new ReviewVO();
				
				vo.setReviewIdx(reviewIdx);
				vo.setMovieIdx(movieIdx);
				vo.setId(id);
				vo.setReviewDate(reviewDate);
				vo.setReviewText(revewText);
				
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}//select_Riveew_All() end
	
	
	
	//TODO : 리뷰작성(김다정)
	public int insert_review(ReviewVO vo) {
			
			int res = 0;
			
			Connection 		  conn  = null;
			PreparedStatement pstmt = null;
			
			String sql = "insert into review values(seq_reviewIdx.nextVal, ?, ?, ?, ?)";
			
			try {
				conn  = DBConnection.getInstance().getConnection();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, vo.getMovieIdx());
				pstmt.setString(2, vo.getId());
				pstmt.setString(3, vo.getReviewDate());
				pstmt.setString(4, vo.getReviewText());
				
				res = pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt!=null) pstmt.close(); 
					if(conn !=null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return res;
		}//insert_review() end
	
	
	
	
	
}//class end