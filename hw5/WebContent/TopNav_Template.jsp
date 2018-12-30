<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-light">
	<a href="home.do" class="navbar-brand text-uppercase"> Blog Master</a>
	<ul class="nav nav-pills">
	<c:choose>
		<c:when test="${user != null}">
		<form method="GET" action="home.do">
			<li class="nav-item">
		   		<button type="submit" class="btn btn-primary"> Home</button>
		 	</li>
	 	</form>
	 	<form method="POST" action="logout.do">
	 		<li class="nav-item">
	    		<button type="submit" class="btn btn-link"> Log out</button>
	  		</li>
  		</form>
  		</c:when>
  		<c:otherwise>
		<form method="GET" action="login.do">
			<li class="nav-item">
		   		<button type="submit" class="btn btn-primary">Login</button>
		 	</li>
	 	</form>
	 	<form method="GET" action="register.do">
	 		<li class="nav-item">
	    		<button type="submit" class="btn btn-link">Sign up</button>
	  		</li>
  		</form>
		</c:otherwise>
	</c:choose>
	</ul>
</nav>