<%@page import="java.util.List"%>
<%@page import="webapp.blog.databean.UserBean"%>
<div class="left">
	<div class="leftcontainer">

<%	
	UserBean[] users = (UserBean[]) request.getAttribute("users");
%>
<%
	for(int i = 0; i < users.length; i++) {
		UserBean user = users[i];
%>		
		<form method="GET" action="Vistor">
			<input type="hidden" name="name" value="<%=user.getFirtname() + " " + user.getLastame()%>">
	    	<button type="submit" class="btn btn-link"> <%=user.getFirtname() + " " + user.getLastame()%></button>
		</form>
<%
	}
%>

	</div>
</div>