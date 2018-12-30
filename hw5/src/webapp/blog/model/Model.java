package webapp.blog.model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;


public class Model {
	private UserDAO userDAO;
	private PostDAO postDAO;
	private CommentDAO commentDAO;
	
	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL = config.getInitParameter("jdbcURL");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
			
			//TODO: create commentDAO
			userDAO = new UserDAO(pool, "zhuoxuew_user");
			postDAO = new PostDAO(pool, "zhuoxuew_post");
			commentDAO = new CommentDAO(pool, "zhuoxuew_comment");
			
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	public PostDAO getPostDAO() {
		return postDAO;
	}
	public CommentDAO getCommentDAO() {
		return commentDAO;
	}
}
