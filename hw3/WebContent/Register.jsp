<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>RegisterPage</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/Layout.css">
</head>
<body>
	<jsp:include page="TopNav_Template_U.html" />
	<div class="divBox">
		<jsp:include page="SideNav_Template_U.html" />
		<div class="right">
			<br><p class="title text-uppercase"> Welcome to Blog Master! </p>
			<div class="rightcontainer"><br>
				<form class="form">
					<div class="form-group">
		            	<label for="InputEmail">Email: </label>
		            	<input type="text" class="form-control" id="InputEmail" placeholder="Email">
		          	</div>	
			        <div class="form-group">
		            	<label for="InputPassword1">Password: </label>
		            	<input type="password" class="form-control" id="InputPassword1" placeholder="Password">
		          	</div>  	
					<div class="form-group">
		            	<label for="InputPassword2">Confirm Password: </label>
		            	<input type="password" class="form-control" id="InputPassword2" placeholder="Password">
		          	</div>
			        <div class="form-group">
		            	<label for="InputLastName">LastName: </label>
		            	<input type="text" class="form-control" id="InputLastName" placeholder="LastName">
		          	</div>
			        <div class="form-group">
		            	<label for="InputFirstName">FirstName: </label>
		            	<input type="text" class="form-control" id="InputFirstName" placeholder="FirstName">
		          	</div>
				  	<button type="button" class="btn btn-primary" onclick="location='Home.jsp'">Submit</button>	
				</form>
			</div>
		</div>
	</div>

</body>
</html>