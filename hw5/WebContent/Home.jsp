<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>HomePage</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/Layout.css">
</head>
<body>
	<jsp:include page="TopNav_Template.jsp" />
	<div class="divBox">
		<jsp:include page="SideNav_Template.jsp" />
		<div class="right">
			<br><h2>${user.getFirstname()} ${user.getLastname()}'s HomePage</h2><br>
			<c:if test="${note!=null}">
				<p style="color: red">${note}</p>
			</c:if>
			<c:forEach var="post" items="${posts}">
				<div class="list-group-item list-group-item-action flex-column">
		    	<div class="d-flex w-100 justify-content-between">
		    		<small class="text-muted">${post.time}</small>
		    	</div>
		    	<p class="mb-1">${post.post}</p>
		    	<div class="d-flex w-100 justify-content-between">
		    		<form method="POST" action="deletepost.do" > 
		    			<input type="hidden" name="post_id" value="${post.post_id}">
		    			<button type="submit" class="btn btn-link btn-sm">delete</button>
		    		</form> 
		    		<small class="text-muted">${post.date}</small>
		    	</div>
		    	<div class="pull-right">
		    	
		    	<c:forEach var="comment" items="${Postmap[post]}">
		    		<form class="text-right" method="POST" action="deletecomment.do" > 
		    			<input type="hidden" name="comment_id" value="${comment.comment_id}">
		    			<small class="text-muted">${comment.name}</small>
		    			${comment.comment}
		    			<small class="text-muted">${comment.date} ${comment.time}</small>
		    			<button type="submit" class="btn btn-link btn-sm">delete</button>
		    		</form>
				</c:forEach>	
		    	</div>
	  		</div>
			</c:forEach>
			  	
			<c:forEach var="error" items="${errors}">
  				<p style="color: red">${error}</p>
  			</c:forEach>
			<form class="form-group" method="POST" action="post.do">
    			<textarea class="form-control" id="Textarea1" rows="3" maxlength="42" name="post" placeholder="No more than 42 characters"></textarea>
    			<br>
    			<input type="hidden" name="name" value="${user.getFirstname()} ${user.getLastname()}">
    			<input class="btn btn-outline-success" type="submit" name="action" value="Post a new blog!">
  			</form>
		</div>
	</div>


</body>
</html>