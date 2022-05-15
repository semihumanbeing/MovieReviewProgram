package MovieDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import vo.UserVO;

public class UserDao {

	static UserDao single = null; // 싱글턴객체생성

	public static UserDao getInstance() {
		if (single == null) {
			single = new UserDao();
		}
		return single;
	}

	private UserDao() {
		// 외부에서 생성불가 (싱글턴)
	}
	
	public int insertSignUp(UserVO vo) { // 회원가입시 유저목록입력
		Connection connection = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		String sql = "insert into users values(?,?)";

		try {
			connection = DBConnection.getInstance().getConnection();
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			
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
	
	public List<UserVO> checkUserInfo(UserVO getVo) { // 로그인시 유저정보확인
		List<UserVO> list = new ArrayList<UserVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		String sql = "select * from users where id =?";
		
		try {
			conn = DBConnection.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, getVo.getId());
			pstmt.executeUpdate();
			
			result = pstmt.executeQuery();
			
			while(result.next()) {
				String id = result.getString("id");
				String pwd = result.getString("pwd");
				UserVO setVo = new UserVO();
				
				setVo.setId(id);
				setVo.setPwd(pwd);
				
				list.add(setVo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(result != null) {
					result.close();
				} if (pstmt != null) {
					pstmt.close();
				} if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public List<UserVO> userList() { // 유저목록확인
		List<UserVO> list = new ArrayList<UserVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from users";
		
		try {
			conn = DBConnection.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				UserVO vo = new UserVO();
				
				vo.setId(id);
				vo.setPwd(pwd);
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs != null) {
					rs.close();
				} if (pstmt != null) {
					pstmt.close();
				} if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}
