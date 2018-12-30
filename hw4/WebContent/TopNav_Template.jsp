<nav class="navbar navbar-light">
	<a href="Home" class="navbar-brand text-uppercase"> Blog Master</a>
	<ul class="nav nav-pills">
<%		if (request.getSession().getAttribute("user") != null) { %>
		<form method="GET" action="Home">
			<li class="nav-item">
		   		<button type="submit" class="btn btn-primary"> Home</button>
		 	</li>
	 	</form>
	 	<form method="POST" action="Logout">
	 		<li class="nav-item">
	    		<button type="submit" class="btn btn-link"> Log out</button>
	  		</li>
  		</form>
<% 		} else { %>
		<form method="GET" action="Login">
			<li class="nav-item">
		   		<button type="submit" class="btn btn-primary">Login</button>
		 	</li>
	 	</form>
	 	<form method="GET" action="Register">
	 		<li class="nav-item">
	    		<button type="submit" class="btn btn-link">Sign up</button>
	  		</li>
  		</form>
<% 		} %>

	</ul>
</nav>