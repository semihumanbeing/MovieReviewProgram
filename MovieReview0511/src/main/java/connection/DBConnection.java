package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	
	static { // driver�� static �ʱ�ȭ�� ���� �ҷ�����
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	static DBConnection single = null;

	
	public static DBConnection getInstance() { 
		
		if(single == null) { // �̱������� getInstance �޼��� ����
			single = new DBConnection();
		}
		return single;
	}
	
	
	private DBConnection() {// �̱��ϰ�ü�� ��������� �ʵ��� private ������ �����
	}
	
	public Connection getConnection() throws SQLException{
		// connection ������
		String url  = "jdbc:oracle:thin:@localhost:1521:xe";  //TODO: DB���� ���� ��������
		String user = "test";  //TODO: ��������
		String pwd  = "test";  //TODO: ��������
		
		Connection conn = DriverManager.getConnection(url,user,pwd);
		return conn;
	}
}