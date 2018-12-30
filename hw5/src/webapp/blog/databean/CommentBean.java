package webapp.blog.databean;

import java.sql.Date;
import java.sql.Time;

import org.genericdao.PrimaryKey;

@PrimaryKey("comment_id")
public class CommentBean {
	private int comment_id;
	private String comment;
	private String owner;
	private Time time;
	private Date date;
	private int post_id;
	private String name;

	
	public int getComment_id() {
		return comment_id;
	}
	public String getComment() {
		return comment;
	}
	public String getOwner() {
		return owner;
	}
	public Time getTime() {
		return time;
	}
	public Date getDate() {
		return date;
	}
	public int getPost_id() {
		return post_id;
	}
	public String getName() {
		return name;
	}
	
	public void setComment_id(int i) {
		comment_id = i;
	}
	public void setComment(String s) {
		comment = s;
	}
	public void setOwner(String s) {
		owner = s;
	}
	public void setTime(Time t) {
		time = t;
	}
	public void setDate(Date d) {
		date = d;
	}
	public void setPost_id(int i) {
		post_id = i;
	}
	public void setName(String s) {
		name = s;
	}

}
