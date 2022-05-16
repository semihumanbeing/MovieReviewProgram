package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	
	static { // driver는 static 초기화를 통해 불러오기
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	static DBConnection single = null;

	
	public static DBConnection getInstance() { 
		
		if(single == null) { // 싱글턴으로 getInstance 메서드 생성
			single = new DBConnection();
		}
		return single;
	}
	
	
	private DBConnection() {// 싱글턴객체가 재생성되지 않도록 private 생성자 만들기
	}
	
	public Connection getConnection() throws SQLException{
		// connection 얻어오기
		String url  = "jdbc:oracle:thin:@localhost:1521:xe";  //TODO: DB에서 정보 가져오기
		String user = "test";  //TODO: 계정연결
		String pwd  = "test";  //TODO: 계정연결
		
		Connection conn = DriverManager.getConnection(url,user,pwd);
		return conn;
	}
}