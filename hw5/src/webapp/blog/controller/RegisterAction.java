package webapp.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.DuplicateKeyException;
import org.genericdao.RollbackException;

import webapp.blog.databean.UserBean;
import webapp.blog.formbean.RegisterForm;
import webapp.blog.model.Model;
import webapp.blog.model.UserDAO;


public class RegisterAction extends Action {
	private UserDAO userDAO;
	
	public RegisterAction(Model model) {
		userDAO = model.getUserDAO();
	}
	
	public String performGet(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			return "home.do";
		}
		try {
			request.setAttribute("users", userDAO.getUsers());
		}catch (RollbackException e) {
            return "register.do";
        }
		return "Register.jsp";
	}
	
	public String performPost(HttpServletRequest request) {
		HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
        	return "home.do";
        }
        
        List<String> errors = new ArrayList<>();
        request.setAttribute("errors", errors);
        System.out.println("start");
        try {
        	RegisterForm form = new RegisterForm(request);
        	request.setAttribute("form", form);
        	request.setAttribute("users", userDAO.getUsers());
        	
        	errors.addAll(form.getValidationErrors());
        	if (errors.size() > 0) {
                return "Register.jsp";
        	}
        	
        	if (form.getButton().equals("Submit")) {
        		UserBean user = new UserBean();
        		user.setEmail(form.getEmail());
        		user.setPassword(form.getPassword1());
        		user.setFirstname(form.getFirstname());
        		user.setLastname(form.getLastname());
    			userDAO.create(user);
    			session.setAttribute("user", user);
    			return "home.do";
        	}
        	
        	// If there is no "submit" action, refresh the resgiter page
        	return "register.do";
        } catch (DuplicateKeyException e) {
            errors.add("Email Existed");
            return "Register.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
            return "Register.jsp";
        }
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "register.do";
	}
}
