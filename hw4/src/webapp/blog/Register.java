package webapp.blog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webapp.blog.dao.MyDAOException;
import webapp.blog.dao.UserDAO;
import webapp.blog.databean.UserBean;
import webapp.blog.formbean.RegisterForm;

@WebServlet("/Register")
public class Register extends HttpServlet{
	 
	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO;
	
	public void init() throws ServletException {
		ServletContext context = getServletContext();
		String jdbcDriverName = context.getInitParameter("jdbcDriverName");
		String jdbcURL = context.getInitParameter("jdbcURL");
		
		try {
			userDAO = new UserDAO(jdbcDriverName, jdbcURL, "zhuoxuew_user");
		} catch (MyDAOException e){
			throw new ServletException(e);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
        	response.sendRedirect("Home");
        	return;
        }
        
        List<String> errors = new ArrayList<>();
        request.setAttribute("errors", errors);
        
        try {
        	RegisterForm form = new RegisterForm(request);
        	request.setAttribute("form", form);
        	request.setAttribute("users", userDAO.getUsers());
        	
        	if ("GET".equals(request.getMethod())) {
        		RequestDispatcher d = request.getRequestDispatcher("Register.jsp");
                d.forward(request, response);
                return;
        	}
        	errors.addAll(form.getValidationErrors());
        	if (errors.size() > 0) {
        		RequestDispatcher d = request.getRequestDispatcher("Register.jsp");
                d.forward(request, response);
                return;
        	}
        	
        	if (form.getButton().equals("Submit")) {
        		UserBean user = new UserBean();
        		user.setEmail(form.getEmail());
        		user.setPassword(form.getPassword1());
        		user.setFirstname(form.getFirstname());
        		user.setLastname(form.getLastname());
        		userDAO.create(user);
        		
        		session.setAttribute("user", user);
        		response.sendRedirect("Home");
        		return;
        	}
        } catch (MyDAOException e) {
            errors.add("Email Existed");
            RequestDispatcher d = request.getRequestDispatcher("Register.jsp");
            d.forward(request, response);
        }
	}
}
