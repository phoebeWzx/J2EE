package webapp.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import webapp.blog.databean.UserBean;
import webapp.blog.formbean.LoginForm;
import webapp.blog.model.UserDAO;
import webapp.blog.model.Model;

public class LoginAction extends Action {
		
	private UserDAO userDAO;
	
	public LoginAction(Model model) {
		userDAO = model.getUserDAO();
	}
	
	public String performGet(HttpServletRequest request) {
        // If user is already logged in, redirect to home.do
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			return "home.do";
		}
		try {
			request.setAttribute("users", userDAO.getUsers());
		}catch (RollbackException e) {
            return "login.do";
        }
		return "Login.jsp";
    }
	
	public String performPost(HttpServletRequest request) {
		// If user is already logged in, redirect to home.do
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			return "home.do";
		}
		
		List<String> errors = new ArrayList<>();
		request.setAttribute("errors", errors);
		
		try {
			LoginForm form = new LoginForm(request);
			request.setAttribute("form", form);
			request.setAttribute("users", userDAO.getUsers());
			
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
            	return "Login.jsp";
            }
            
            // Look up the user
            UserBean user = userDAO.read(form.getEmail());
            if (user == null) {
            	errors.add("No such user!!!");
            	return "Login.jsp";
            }
            
            // Check the password
            if (!form.getPassword().equals(user.getPassword())) {
                errors.add("Incorrect password or username");
                return "Login.jsp";
            }
            
            // Attach the user bean to the session
            session.setAttribute("user", user);
            return "home.do";
     
		} catch (RollbackException e) {
            errors.add(e.getMessage());
            return "login.do";
        }
		
	}

	@Override
	public String getName() {
		return "login.do";
	}
}
