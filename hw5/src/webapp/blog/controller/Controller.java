package webapp.blog.controller;

import webapp.blog.databean.UserBean;
import webapp.blog.model.Model;

import javax.servlet.http.HttpServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
		Model model = new Model(getServletConfig());
		
		Action.add(new LoginAction(model));
		Action.add(new RegisterAction(model));
		Action.add(new HomeAction(model));
		Action.add(new VistorAction(model));
		Action.add(new LogoutAction(model));
		Action.add(new PostAction(model));
		Action.add(new CommentAction(model));
		Action.add(new DeletepostAction(model));
		Action.add(new DeletecommentAction(model));
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String nextPage = performTheAction(request);
		sendtoNextPage(nextPage, request, response);
	}

	private void sendtoNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		// TODO Auto-generated method stub
		if (nextPage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,
					request.getServletPath());
			return;
		}
		
		if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
		}
		if (nextPage.endsWith(".jsp")) {
			RequestDispatcher d = request.getRequestDispatcher(nextPage);
			d.forward(request, response);
			return;
		}
		
		throw new ServletException(Controller.class.getName()
                + ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
	}

	private String performTheAction(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String servletPath = request.getServletPath();
		UserBean user = (UserBean) session.getAttribute("user");
		String action = getActionName(servletPath);
		
		
		if (user != null) {
			//Let the logged in user run his action
			return Action.perform(action, request);
		}
		
		if (action.equals("register.do")) {
			return Action.perform("register.do", request);
		}
		
		if (action.equals("vistor.do")) {
			return Action.perform("vistor.do", request);
		}
		
		if (action.equals("post.do")) {
			return Action.perform("post.do", request);
		}
		if (action.equals("comment.do")) {
			return Action.perform("comment.do", request);
		}
		if (action.equals("deletepost.do")) {
			return Action.perform("deletepost.do", request);
		}
		if (action.equals("deletecomment.do")) {
			return Action.perform("deletecomment.do", request);
		}
		
		/*if (action.equals("login.do")) {
			return Action.perform("login.do", request);
		}*/
		// TODO:
		//return "Login.jsp";
		return Action.perform("login.do", request);
	}

	private String getActionName(String servletPath) {
		// TODO Auto-generated method stub
		int slash =servletPath.lastIndexOf("/");
		return servletPath.substring(slash + 1);
	}
}
