package webapp.blog.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;

import webapp.blog.databean.UserBean;

public class UserDAO extends GenericDAO<UserBean>{	
    public UserDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(UserBean.class, tableName, cp);
    }
    public UserBean[] getUsers() throws RollbackException {
    	UserBean[] users = match();	
    	return users;
    }
}
