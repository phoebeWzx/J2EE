package webapp.blog.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.genericdao.RollbackException;

import webapp.blog.databean.CommentBean;
import webapp.blog.databean.PostBean;
import webapp.blog.databean.UserBean;
import webapp.blog.model.CommentDAO;
import webapp.blog.model.Model;
import webapp.blog.model.PostDAO;
import webapp.blog.model.UserDAO;

public class HomeAction extends Action {
	
	private UserDAO userDAO;
	private PostDAO postDAO;
	private CommentDAO commentDAO;
	
	public HomeAction(Model model) {
		userDAO = model.getUserDAO();
		postDAO = model.getPostDAO();
		commentDAO = model.getCommentDAO();
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
			UserBean cur_user = (UserBean) session.getAttribute("user");
			request.setAttribute("users", userDAO.getUsers());
			
			Map<PostBean, List<CommentBean>> map = new HashMap<>();
			PostBean[] posts = (PostBean[]) postDAO.getPosts(cur_user.getEmail());
			
			for (int i = 0; i < posts.length; i++) {
				CommentBean[] comments = (CommentBean[]) commentDAO.getComments(posts[i].getPost_id());
				List<CommentBean> list = new ArrayList<>();
				for (int j = 0; j < comments.length; j++) {
					list.add(comments[j]);
				}
				map.put(posts[i], list);
			}
			
			request.setAttribute("Postmap", map);
			request.setAttribute("posts", posts);
			
		} catch (RollbackException e) {
			errors.add(e.getMessage());
        	request.setAttribute("errors", errors);
            return "error.jsp";
        }
		return "Home.jsp";
	}

	@Override
	public String getName() {
		return "home.do";
	}
}
