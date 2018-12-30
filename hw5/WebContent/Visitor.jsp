<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Visitor Page</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/Layout.css">
</head>
<body>
	<jsp:include page="TopNav_Template.jsp" />
	<div class="divBox">
		<jsp:include page="SideNav_Template.jsp" />
		<div class="right">
		
			<c:choose>
			<c:when test="${name != null }">
				<br><h2>${name} 's Blog</h2><br>
			</c:when>
			<c:otherwise>
				<br><h2>${param.name} 's Blog</h2><br>
			</c:otherwise>
			</c:choose>
			
			<c:forEach var="error" items="${errors}">
  				<p style="color: red">${error}</p>
  			</c:forEach>
			<c:forEach var="post" items="${posts}">
	  		<div class="list-group-item list-group-item-action flex-column">
		    	<div class="d-flex w-100 justify-content-between">
		    		<small class="text-muted">${post.time}</small>
		    	</div>
		    	<p class="mb-1">${post.post}</p>
		    	<div class="d-flex w-100 justify-content-between">
		    		
		    		<small class="text-muted">${post.date}</small>
		    	</div>
		    	<div class="pull-right">
		    	
		    		<c:forEach var="comment" items="${Postmap[post]}">
		    		<form class="text-right" method="POST" action="deletecomment.do" >
		    			<small class="text-muted">${comment.name}</small>
		    			${comment.comment}
		    			<small class="text-muted">${comment.date} ${comment.time}</small>
		    			
		    			<c:if test="${user != null}">
		    				<c:if test="${user.email eq comment.owner}">
			    				<input type="hidden" name="comment_id" value="${comment.comment_id}">
			    				<button type="submit" class="btn btn-link btn-sm">delete</button>
		    				</c:if>
		    			</c:if>
		    			
		    		</form>
		    		</c:forEach>
		    		
		    		
		    		<form class="form-inline" method="POST" action="comment.do">
		    			<div class="form-group">
		    				<input type="hidden" name="name" value="${user.getFirstname()} ${user.getLastname()}" %>
		    				<input type="hidden" name="post_id" value="${post.post_id}">
		    				<input type="hidden" name="name" value="${post.name }">
		    				<input type="text" class="form-control" id="InputEmail" name="comment" placeholder="comment">
		    			</div>
		    		
					<c:choose>
						<c:when test="${user != null}">
							
							<input type="submit" class="btn btn-success" name="action" value="Comment">
						</c:when>
						<c:otherwise>
							
							<input type="submit" class="btn btn-success" name="action" value="Please login to Comment">
						</c:otherwise>
					</c:choose>
					</form>	
		    	</div>
	  		</div>
	  		
	  		</c:forEach>	  		
	  		
		</div>
	</div>


</body>
</html>