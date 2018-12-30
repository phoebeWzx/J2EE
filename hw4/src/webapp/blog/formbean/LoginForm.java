package webapp.blog.formbean;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

public class LoginForm {
	private String email;
	private String password;
	private String button;
	
	public LoginForm(HttpServletRequest request) {
		email = request.getParameter("email");
		password = request.getParameter("password");
		button = request.getParameter("button");
	}
	
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getButton() {
        return button;
    }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<>();
		
		if (email == null || email.length() == 0) {
			errors.add("Email is required");
		}
		
		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		}
		
		if (button == null) {
            errors.add("Action is required");
        }
		
		if (errors.size() > 0) {
			return errors;
		}
		
		if (!button.equals("Login")) {
			errors.add("Invalid action");
		}
		
		if (!email.matches("^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$")) {
            errors.add("Invalid email");
        }
		
		if (email.matches(".*[<>\"].*")) {
            errors.add("User Name may not contain angle brackets or quotes");
        }
        
        if (password.matches(".*[<>\"].*")) {
            errors.add("Password may not contain angle brackets or quotes");
        }
        
		return errors;
	}
}
