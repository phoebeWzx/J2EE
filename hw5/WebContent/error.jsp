<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>BLOG MASTER -- Error Page</title>
    </head>
    
	<body>
	
		<h2>BLOG MASTER Error</h2>

        <c:forEach var="error" items="${errors}">
  				<p style="color: red">${error}</p>
  			</c:forEach>
        
		<c:choose>
			<c:when test="${user==null}">
				Click <a href="login.do">here</a> to login.
			</c:when>
			<c:otherwise>
				Click <a href="home.do">here</a> to return to the HomePage.
			</c:otherwise>
		</c:choose>

	</body>
</html>