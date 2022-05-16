package MovieDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.DBConnection;
import vo.ReviewVO;
import vo.UserVO;

public class ReviewDao {

	static ReviewDao single = null; // 教臂畔按眉积己

	public static ReviewDao getInstance() {
		if (single == null) {
			single = new ReviewDao();
		}
		return single;
	}

	private ReviewDao() {
		// 寇何俊辑 积己阂啊 (教臂畔)
	}
	
	public int updateReview(ReviewVO vo) { 
		Connection connection = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		String sql = "update reveiw set reviewText='?' where reviewIdx = select";

		try {
			connection = DBConnection.getInstance().getConnection();
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getReviewIdx());
			pstmt.setString(2, vo.getReviewText());
						
			rs = pstmt.executeUpdate();
			
			


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;

	}
	
	public int DeleteReview(ReviewVO vo) { 
		Connection connection = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		String sql = "delete reveiw where select = reviewIdx";

		try {
			connection = DBConnection.getInstance().getConnection();
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getReviewIdx());
			
			rs = pstmt.executeUpdate();


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;

	}
}	 