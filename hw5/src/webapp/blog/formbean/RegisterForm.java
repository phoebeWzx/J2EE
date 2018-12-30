package webapp.blog.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class RegisterForm {
	private String email;
	private String password1;
	private String password2;
	private String lastname;
	private String firstname;
	private String button;
	
	public RegisterForm(HttpServletRequest request) {
		email = request.getParameter("email");
		password1 = request.getParameter("password1");
		password2 = request.getParameter("password2");
		lastname = request.getParameter("lastname");
		firstname = request.getParameter("firstname");
		button = request.getParameter("button");
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword1() {
		return password1;
	}
	
	public String getPassword2() {
		return password2;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getButton() {
		return button;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<>();
		if (email == null || email.length() == 0) {
			errors.add("Email is required");
		}
		if (password1 == null || password1.length() == 0 || password2 == null || password2.length() == 0) {
			errors.add("Password is required");
		}
		if (!password1.equals(password2)) {
			errors.add("Password is not matched");
		}
		if (lastname == null || lastname.length() == 0) {
			errors.add("Lastname is required");
		}
		if (firstname == null || firstname.length() == 0) {
			errors.add("Firstname is required");
		}
		if (button == null) {
            errors.add("Action is required");
        }
		
		if (errors.size() > 0) {
            return errors;
        }
		
		if (!button.equals("Submit")) {
			errors.add("Invalid action");
		}
		if (!email.matches("^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$")) {
            errors.add("Invalid email");
        }
		if (email.matches(".*[<>\"].*")) {
            errors.add("User Name may not contain angle brackets or quotes");
        }

        if (firstname.matches(".*[<>\"].*")) {
            errors.add("Firstname may not contain angle brackets or quotes");
        }
        
        if (lastname.matches(".*[<>\"].*")) {
            errors.add("Lastname  may not contain angle brackets or quotes");
        }
        
        if (password1.matches(".*[<>\"].*")) {
            errors.add("Password may not contain angle brackets or quotes");
        }
        
        if (password2.matches(".*[<>\"].*")) {
            errors.add("Confirmed password may not contain angle brackets or quotes");
        }

		return errors;
	}
}
