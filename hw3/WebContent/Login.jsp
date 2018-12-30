<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>LoginPage</title>
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
						<label for="InputEmail">Email:</label>
						<input type="text" class="form-control" id="InputEmail" placeholder="email">
					</div>
					<div class="form-group">
						<label for="InputPassword">Password:</label>
						<input type="password" class="form-control" id="InputPassword" placeholder="Password">
					</div>
					<div class="form-group form-check">
					    <input type="checkbox" class="form-check-input" id="Check">
					    <label class="form-check-label" for="Check">Remember me</label>
					</div>
					<button type="button" class="btn btn-primary" onclick="location='Home.jsp'">Login</button>	
				</form>
			</div>
		</div>
	</div>

</body>
</html>