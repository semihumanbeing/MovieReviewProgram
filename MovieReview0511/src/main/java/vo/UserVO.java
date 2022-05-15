package vo;

public class UserVO {
	String id;
	String pwd;
	
	
	public UserVO() {
		super();
	}
	public UserVO(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}
	public UserVO(String id) {
		this.id = id;
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	

}
