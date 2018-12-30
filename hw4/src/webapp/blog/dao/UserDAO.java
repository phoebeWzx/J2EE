package webapp.blog.dao;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import webapp.blog.dao.MyDAOException;
import webapp.blog.databean.UserBean;

public class UserDAO {
	private List<Connection> connectionPool = new ArrayList<Connection>();
	
	private String jdbcDriver;
    private String jdbcURL;
    private String tableName;
    
    public UserDAO(String jdbcDriver, String jdbcURL, String tableName)
            throws MyDAOException {
        this.jdbcDriver = jdbcDriver;
        this.jdbcURL = jdbcURL;
        this.tableName = tableName;
        
        
        if (!tableExists()) {
        	System.out.println("Table does not exit");
            createTable();
        }
    }
    
    private synchronized Connection getConnection() throws MyDAOException {
        if (connectionPool.size() > 0) {
            return connectionPool.remove(connectionPool.size() - 1);
        }

        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            throw new MyDAOException(e);
        }

        try {
            return DriverManager.getConnection(jdbcURL);
        } catch (SQLException e) {
            throw new MyDAOException(e);
        }
    }
    
    private synchronized void releaseConnection(Connection con) {
        connectionPool.add(con);
    }
    
    public void create(UserBean user) throws MyDAOException {
    	Connection con = null;
    	try {
    		con = getConnection();
    		String sql = "INSERT INTO " + tableName +" (email,password,firstname,lastname) VALUES (?,?,?,?)";
    		PreparedStatement pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, user.getEmail());
    		pstmt.setString(2, user.getPassword());
    		pstmt.setString(3, user.getFirtname());
    		pstmt.setString(4, user.getLastame());
    		
    		int count = pstmt.executeUpdate();
    		if (count != 1) {
    			throw new SQLException("Insert updated " + count + " rows");
    		}
    		
    		pstmt.close();
    		releaseConnection(con);
    	} catch (Exception e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }
    }
    private void createTable() throws MyDAOException {
    	Connection con = null;
    	try {
    		con = getConnection();
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate("CREATE TABLE "
                    + tableName
                    + " (email VARCHAR(255) NOT NULL, password VARCHAR(255), lastname VARCHAR(255), "
                    + "firstname VARCHAR(255), PRIMARY KEY(email))");
    		stmt.close();
    		
    		releaseConnection(con);
    	}catch (SQLException e) {
    		try {
    			if (con != null) {
    				con.close();
    			}
    		}catch (SQLException e2) {}
    		throw new MyDAOException(e);
    	}
    }
    
    private boolean tableExists() throws MyDAOException {
    	Connection con = null;
    	try {
    		con = getConnection();
    		DatabaseMetaData metaData = con.getMetaData();
    		ResultSet rs = metaData.getTables(null, null, tableName, null);
    		
    		boolean ans = rs.next();
    		
    		rs.close();
    		releaseConnection(con);
    		
    		return ans;
    	} catch (SQLException e) {
    		try {
    			if (con != null) {
    				con.close();
    			}
    		}catch (SQLException e2) {}
    		throw new MyDAOException(e);
    	}
    }
    
    public UserBean read(String email) throws MyDAOException {
    	Connection con = null;
    	try {
    		con = getConnection();
    		String sql = "SELECT * FROM " + tableName + " WHERE email=?";
    		PreparedStatement pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, email);
    		ResultSet rs = pstmt.executeQuery();
    		
    		UserBean user;
    		if (!rs.next()) {
    			user = null;
    		} else {
    			user = new UserBean();
    			user.setEmail(rs.getString("email"));
    			user.setPassword(rs.getString("password"));
    			user.setFirstname(rs.getString("firstname"));
    			user.setLastname(rs.getString("lastname"));
    		}
    		rs.close();
    		pstmt.close();
    		releaseConnection(con);
    		return user;
    	} catch (Exception e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }
    }
    public UserBean[] getUsers() throws MyDAOException {
    	Connection con = null;
    	try {
    		con = getConnection();
    		String sql = "SELECT * FROM " + tableName;
    		PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            List<UserBean> list = new ArrayList<>();
            while(rs.next()) {
            	UserBean user = new UserBean();
            	user.setFirstname(rs.getString("firstname"));
            	user.setLastname(rs.getString("lastname"));
            	list.add(user);
            }
            pstmt.close();
            releaseConnection(con);
            
            return list.toArray(new UserBean[list.size()]);
    	} catch (SQLException e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }
    }
    
}
