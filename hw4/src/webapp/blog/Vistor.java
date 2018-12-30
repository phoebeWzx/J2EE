package webapp.blog;

import java.io.IOException;

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

@WebServlet("/Vistor")
public class Vistor extends HttpServlet{
	
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
		
		try {
			request.getParameter("name");
			request.setAttribute("users", userDAO.getUsers());
			
		} catch (MyDAOException e) {
            RequestDispatcher d = request.getRequestDispatcher("Visitor.jsp");
            d.forward(request, response);
        }

		if ("GET".equals(request.getMethod())) {
            RequestDispatcher d = request.getRequestDispatcher("Visitor.jsp");
            d.forward(request, response);
            return;
        }
		 
	}
}
