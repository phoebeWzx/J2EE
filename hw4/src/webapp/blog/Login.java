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
import webapp.blog.formbean.LoginForm;

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO;
	
	public void init() throws ServletException {
        ServletContext context = getServletContext();
        String jdbcDriverName = context.getInitParameter("jdbcDriverName");
        String jdbcURL = context.getInitParameter("jdbcURL");

        try {
            userDAO = new UserDAO(jdbcDriverName, jdbcURL, "zhuoxuew_user");
        } catch (MyDAOException e) {
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
			LoginForm form = new LoginForm(request);
			request.setAttribute("form", form);
			request.setAttribute("users", userDAO.getUsers());
			
			
            if ("GET".equals(request.getMethod())) {
                RequestDispatcher d = request.getRequestDispatcher("Login.jsp");
                d.forward(request, response);
                return;
            }
            
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
            	RequestDispatcher d = request.getRequestDispatcher("Login.jsp");
            	d.forward(request, response);
            	return;
            }
            
            UserBean user = userDAO.read(form.getEmail());
            if (user == null) {
            	errors.add("No such user");
            	RequestDispatcher d = request.getRequestDispatcher("Login.jsp");
            	d.forward(request, response);
            	return;
            }
            
            if (!form.getPassword().equals(user.getPassword())) {
                errors.add("Incorrect password or username");
                RequestDispatcher d = request.getRequestDispatcher("Login.jsp");
                d.forward(request, response);
                return;
            }
            
            session.setAttribute("user", user);
            response.sendRedirect("Home");
     
		} catch (MyDAOException e) {
            errors.add(e.getMessage());
            RequestDispatcher d = request.getRequestDispatcher("Login.jsp");
            d.forward(request, response);
        }
		
	}
}
