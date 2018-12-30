package webapp.blog.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import webapp.blog.databean.PostBean;
import webapp.blog.databean.UserBean;
import webapp.blog.formbean.PostForm;
import webapp.blog.model.Model;
import webapp.blog.model.PostDAO;
import webapp.blog.model.UserDAO;

public class PostAction extends Action{
	
	private PostDAO postDAO;
	private UserDAO userDAO;
	
	public PostAction(Model model) {
		postDAO = model.getPostDAO();
		userDAO = model.getUserDAO();
	}
	@Override
	public String getName() {
		return "post.do";
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
        	PostForm form = new PostForm(request);
        	request.setAttribute("form", form);
        	request.setAttribute("users", userDAO.getUsers());
        	request.setAttribute("posts", postDAO.getPosts(cur_user.getEmail()));
        	errors.addAll(form.getValidationErrors());
            if (errors.size() > 0) {
                return "Home.jsp";
            }
           
            java.util.Date utilDate=new Date();      
            java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());        
            java.sql.Time sTime=new java.sql.Time(utilDate.getTime());   
            
            PostBean new_post = new PostBean();
            new_post.setPost(form.getPost());
            new_post.setDate(sqlDate);
            new_post.setTime(sTime);
            new_post.setOwner(cur_user.getEmail());
            new_post.setName(form.getName());
            
            if (form.getAction().equals("Post a new blog!")) {
            	postDAO.add(new_post);
            }
            request.setAttribute("posts", postDAO.getPosts(cur_user.getEmail()));
            return "home.do";
        	
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	request.setAttribute("errors", errors);
            return "error.jsp";
        }
	}

}
