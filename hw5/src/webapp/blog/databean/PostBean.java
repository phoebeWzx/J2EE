package webapp.blog.databean;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.genericdao.PrimaryKey;

@PrimaryKey("post_id")
public class PostBean {
	private int post_id;
	private String post;
	private Time time;
	private Date date;
	private String owner;
	private String name;
	//private List<CommentBean> comments;
	
	public int getPost_id() {
		return post_id;
	}
	
	public String getPost() {
		return post;
	}
	
	public Time getTime() {
		return time;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getOwner() {
		return owner;
	}
	public String getName() {
		return name;
	}
	
	/*public List<CommentBean> getComments() {
		return comments;
	}*/
	
	public void setPost_id(int i) {
		post_id = i;
	}
	
	public void setTime(Time t) {
		time = t;
	}
	
	public void setDate(Date d) {
		date = d;
	}
	
	public void setPost(String s) {
		post = s;
	}
	
	public void setOwner(String user) {
		owner = user;
	}
	public void setName(String s) {
		name = s;
	}
	
	/*public void setComments(ArrayList<CommentBean> list) {
		comments = list;
	}*/
	
	
}
