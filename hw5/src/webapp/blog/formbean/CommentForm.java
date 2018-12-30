package webapp.blog.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class CommentForm {
	private String action;
	private String comment;
	private int post_id;
	private String name;
	
	public CommentForm(HttpServletRequest request) {
		action = request.getParameter("action");
		comment = request.getParameter("comment");
		post_id = Integer.parseInt(request.getParameter("post_id"));
		name = request.getParameter("name");
	}
	
	public String getAction() {
		return action;
	}
	
	public String getComment() {
		return comment;
	}
	
	public int getPost_id() {
		return post_id;
	}
	public String getName() {
		return name;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<>();
		
		if (comment == null || comment.length() == 0) {
			errors.add("Comment is required");
		}
		
		if (action == null) {
			errors.add("Acction is required");
		} else if (!action.equals("Comment") && !action.equals("Please login to Comment")) {
			errors.add("Invalid action: " + action);
		}
		return errors;
	}	
}
