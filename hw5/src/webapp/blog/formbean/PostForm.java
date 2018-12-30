package webapp.blog.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class PostForm {
	private String action;
	private String post;
	private String name;
	
	public PostForm(HttpServletRequest request) {
		action = request.getParameter("action");
		post = request.getParameter("post");
		name = request.getParameter("name");
	}
	
	public String getAction() {
		return action;
	}
	
	public String getPost() {
		return post;
	}
	
	public String getName() {
		return name;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<>();
		
		if (post == null || post.length() == 0) {
			errors.add("Post is required");
		}
		
		if (action == null) {
			errors.add("Acction is required");
		} else if (!action.equals("Post a new blog!")) {
			errors.add("Invalid action: " + action);
		}
		return errors;
	}
}
