<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>RegisterPage</title>
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
				<form class="form" action = "Register" method = "POST">
					<div class="form-group">
		            	<label for="InputEmail">Email: </label>
		            	<input type="email" class="form-control" id="InputEmail" name="email" placeholder="Email">
		          	</div>	
			        <div class="form-group">
		            	<label for="InputPassword1">Password: </label>
		            	<input type="password" class="form-control" id="InputPassword1" name="password1" placeholder="Password">
		          	</div>  	
					<div class="form-group">
		            	<label for="InputPassword2">Confirm Password: </label>
		            	<input type="password" class="form-control" id="InputPassword2" name="password2" placeholder="Password">
		          	</div>
			        <div class="form-group">
		            	<label for="InputLastName">LastName: </label>
		            	<input type="text" class="form-control" id="InputLastName" name="lastname" placeholder="LastName">
		          	</div>
			        <div class="form-group">
		            	<label for="InputFirstName">FirstName: </label>
		            	<input type="text" class="form-control" id="InputFirstName" name="firstname" placeholder="FirstName">
		          	</div>
				  	<input type="submit" class="btn btn-primary" name="button" value="Submit">	
				</form>
			</div>
		</div>
	</div>

</body>
</html>