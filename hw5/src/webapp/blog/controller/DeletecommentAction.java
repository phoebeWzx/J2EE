package webapp.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import webapp.blog.model.CommentDAO;
import webapp.blog.model.Model;

public class DeletecommentAction extends Action {

	private CommentDAO commentDAO;
	
	public DeletecommentAction(Model model) {
		commentDAO = model.getCommentDAO();
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "deletecomment.do";
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
        	int comment_id = Integer.parseInt(request.getParameter("comment_id"));
            commentDAO.delete(comment_id);
            return "home.do";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	request.setAttribute("errors", errors);
            return "error.jsp";
        }
	}
}
