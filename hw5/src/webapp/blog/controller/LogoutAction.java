package webapp.blog.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import webapp.blog.model.Model;



public class LogoutAction extends Action{

	//private UserDAO userDAO;
	
	public LogoutAction(Model model) {
		//userDAO = model.getUserDAO();
	}
	
    public String performPost(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.setAttribute("user", null);
        /*try {
			request.setAttribute("users", userDAO.getUsers());
		} catch (RollbackException e) {
			return "logout.do";
        }*/
        return "login.do";
        
    }
    
    public String performGet(HttpServletRequest request) {
    	return performPost(request);
    }

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "logout.do";
	}
}
