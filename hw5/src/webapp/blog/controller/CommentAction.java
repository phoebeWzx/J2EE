package webapp.blog.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import webapp.blog.databean.CommentBean;
import webapp.blog.databean.PostBean;
import webapp.blog.databean.UserBean;
import webapp.blog.formbean.CommentForm;
import webapp.blog.model.CommentDAO;
import webapp.blog.model.Model;
import webapp.blog.model.PostDAO;
import webapp.blog.model.UserDAO;

public class CommentAction extends Action{
	
	private UserDAO userDAO;
	private CommentDAO commentDAO;
	private PostDAO postDAO;
	
	public CommentAction(Model model) {
		userDAO = model.getUserDAO();
		commentDAO = model.getCommentDAO();
		postDAO = model.getPostDAO();
	}
	
	@Override
	public String getName() {
		return "comment.do";
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
        
        UserBean cur_user = (UserBean) session.getAttribute("user");
        request.setAttribute("user", cur_user);
        
        try {
        	CommentForm form = new CommentForm(request);
        	int post_id = form.getPost_id();
        	request.setAttribute("form", form);
        	request.setAttribute("users", userDAO.getUsers());
        	
        	if(form.getAction().equals("Please login to Comment")) {
            	return "login.do";
            }
        	
            request.setAttribute("name", postDAO.getName(post_id));
            
            Map<PostBean, List<CommentBean>> map = new HashMap<>();
			PostBean[] posts = (PostBean[]) postDAO.getPosts(postDAO.getEmail(post_id));
			
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
        	
        	errors.addAll(form.getValidationErrors());
            if (errors.size() > 0) {
                return "Visitor.jsp";
            }
            
            java.util.Date utilDate=new Date();      
            java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());        
            java.sql.Time sTime=new java.sql.Time(utilDate.getTime());
            
            CommentBean new_comment = new CommentBean();
            new_comment.setComment(form.getComment());
            new_comment.setName(form.getName());
            new_comment.setDate(sqlDate);
            new_comment.setTime(sTime);
            new_comment.setOwner(cur_user.getEmail());
            new_comment.setPost_id(post_id);;
        	
            if(form.getAction().equals("Comment")) {
            	commentDAO.add(new_comment);
            }
            
            posts = (PostBean[]) postDAO.getPosts(postDAO.getEmail(post_id));
			
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

            return "Visitor.jsp";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
        	request.setAttribute("errors", errors);
            return "error.jsp";
        }
	}
}
