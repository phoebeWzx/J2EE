package webapp.blog.model;

import java.util.Arrays;
import java.util.Comparator;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import webapp.blog.databean.CommentBean;

public class CommentDAO extends GenericDAO<CommentBean>{

	public CommentDAO(ConnectionPool cp, String tableName)
			throws DAOException {
		super(CommentBean.class, tableName, cp);
		// TODO Auto-generated constructor stub
	}
	
	public void add(CommentBean comment) throws RollbackException {
		try {
			Transaction.begin();
			create(comment);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}
	}
	
	
	public CommentBean[] getComments(int post) throws RollbackException {
		CommentBean[] comments = match(MatchArg.equals("post_id", post));
		Arrays.sort(comments, new Comparator<CommentBean>() {
			@Override
			public int compare(webapp.blog.databean.CommentBean o1, webapp.blog.databean.CommentBean o2) {
				return o1.getDate().compareTo(o2.getDate()) == 0 ? o1.getTime().compareTo(o2.getTime()) : o1.getDate().compareTo(o2.getDate());
			}});
		
		return comments;
	}

}
