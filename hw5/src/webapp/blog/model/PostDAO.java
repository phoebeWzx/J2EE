package webapp.blog.model;

import java.util.Arrays;
import java.util.Comparator;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import webapp.blog.databean.PostBean;

public class PostDAO extends GenericDAO<PostBean>{

	public PostDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(PostBean.class, tableName, cp);
	}
	
	public void add(PostBean post) throws RollbackException {
		try {
			Transaction.begin();
			create(post);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}
	}
	
	public PostBean[] getPosts(String user) throws RollbackException {
		PostBean[] posts = match(MatchArg.equals("owner", user));
		Arrays.sort(posts, new Comparator<PostBean>() {
			@Override
			public int compare(webapp.blog.databean.PostBean o1, webapp.blog.databean.PostBean o2) {
				return -o1.getDate().compareTo(o2.getDate()) == 0 ? -o1.getTime().compareTo(o2.getTime()) : -o1.getDate().compareTo(o2.getDate());
			}});
		
		return posts;
	}
	public String getName(int post_id) throws RollbackException {
		PostBean[] res = match(MatchArg.equals("post_id", post_id));
		if (res == null) {
			return null;
		}else {
			return res[0].getName();
		}
	}
	public String getEmail(int post_id) throws RollbackException {
		PostBean[] res = match(MatchArg.equals("post_id", post_id));
		if (res == null) {
			return null;
		}else {
			return res[0].getOwner();
		}
	}
}
