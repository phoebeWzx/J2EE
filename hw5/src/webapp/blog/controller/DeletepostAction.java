package webapp.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;


import webapp.blog.model.Model;
import webapp.blog.model.PostDAO;

public class DeletepostAction extends Action{
	private PostDAO postDAO;
	
	public DeletepostAction(Model model) {
		postDAO = model.getPostDAO();
	}
	@Override
	public String getName() {
		return "deletepost.do";
	}
	
	public String performGet(HttpServletRequest request) {
		return performPost(request);
	}
	
	public String performPost(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
			return "login.do";
		}
       
        try {
        	int post_id = Integer.parseInt(request.getParameter("post_id"));
            postDAO.delete(post_id);
            return "home.do";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	request.setAttribute("errors", errors);
            return "error.jsp";
        }
	}

}
