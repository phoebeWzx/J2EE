package webapp.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public abstract class Action {
	
	/**
	 * Used to match request in the map
	 * @return the name of action
	 */
	public abstract String getName();
	
	public String performGet(HttpServletRequest request) {
		request.setAttribute("message", 
				"No implementation of performGet() for action " + getName());
		return "action-error-message.jsp";
	}
	
	public String performPost(HttpServletRequest request) {
		request.setAttribute("message", 
				"No implementation of performPost() for action " + getName());
		return "action-error-message.jsp";
	}
	
	private static Map<String, Action> hash = new HashMap<>();
	
	/**
	 * Adds an action into the map
	 * @param a action
	 */
	public static void add(Action a) {
		synchronized (hash) {
			if (hash.get(a.getName()) != null) {
				throw new AssertionError("Two actions with the same name ("
						+ a.getName() + "):" + a.getClass().getName()
						+ "and " + hash.get(a.getName()).getClass().getName());
			}
			hash.put(a.getName(), a);
		}
	}
	
	public static String perform(String name, HttpServletRequest request) {
		Action a;
		synchronized (hash) {
			a = hash.get(name);
		}
		if (a == null) {
			request.setAttribute("message", 
					"No implementation of performGet() for action " + name);
			return "action-error-message.jsp";
		}
		if ("GET".equals(request.getMethod())) {
			return a.performGet(request);
		}
		if ("POST".equals(request.getMethod())) {
			return a.performPost(request);
		}
		
		request.setAttribute("message",
                "Unexpected HTTP Method (\"" + request.getMethod() + "\") for \"" + name + "\"");
		return "action-error-message.jsp";
	}
	
}
