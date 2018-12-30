<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>LoginPage</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/Layout.css">
</head>
<body>
	<jsp:include page="TopNav_Template.jsp" />
	<div class="divBox">
		<jsp:include page="SideNav_Template.jsp" />
		<div class="right">
			<br><p class="title text-uppercase"> Welcome to Blog Master! </p>
			<div class="rightcontainer"><br>
<% 
			List<String> errors = (List<String>) request.getAttribute("errors");
			if (errors != null) {
				for (String error : errors) {
%>
					<p style="color:red"> <%= error %> </p>
<%
				}
			}
%>			
				<form class="form" action = "Login" method = "POST">
					<div class="form-group">
						<label for="InputEmail">Email:</label>
						<input type="email" class="form-control" name="email" id="InputEmail" aria-describedby="emailHelp" placeholder="email" value="${form.email}">
					</div>
					<div class="form-group">
						<label for="InputPassword">Password:</label>
						<input type="password" class="form-control" name="password" id="InputPassword" placeholder="Password">
					</div>
					<div class="form-group form-check">
					    <input type="checkbox" class="form-check-input" id="Check">
					    <label class="form-check-label" for="Check">Remember me</label>
					</div>
					<input type="submit" class="btn btn-primary" name="button" value="Login">	
				</form>
			</div>
		</div>
	</div>

</body>
</html>