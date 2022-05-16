package MovieDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import vo.MovieVO;
import vo.selectAllVO;

public class MovieDao {

	static MovieDao single = null;
	
	public static MovieDao getInstance() {
		
		if(single==null) {
			single = new MovieDao();
		}
		
		return single;
	}//static 초기화 

	
	private MovieDao() {
	
	}
	
	//selectAll_list() : 3개의 Table 전체조회
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

	
	
	
	
	
}