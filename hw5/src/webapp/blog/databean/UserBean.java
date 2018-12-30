package webapp.blog.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("email")
public class UserBean {
	private String email;
	private String password;
	private String lastname;
	private String firstname;
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setEmail(String s) {
		email = s;
	}
	public void setPassword(String s) {
		password = s;
	}
	public void setLastname(String s) {
		lastname = s;
	}
	public void setFirstname(String s) {
		firstname = s;
	}
}
