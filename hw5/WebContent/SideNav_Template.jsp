<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="left">
	<div class="leftcontainer">

		
		<c:forEach var="user" items="${users}">
		<form method="GET" action="vistor.do">
			<input type="hidden" name="email" value="${user.email}">
			<input type="hidden" name="name" value="${user.firstname} ${user.lastname}">
	    	<button type="submit" class="btn btn-link">${user.firstname} ${user.lastname}</button>
		</form>
		</c:forEach>


	</div>
</div>