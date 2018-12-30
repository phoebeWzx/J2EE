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
			<br><h2><%=request.getParameter("name")%> </>'s Blog</h2><br>
	  		<div class="list-group-item list-group-item-action flex-column">
		    	<div class="d-flex w-100 justify-content-between">
		    		<small class="text-muted">09:30 am</small>
		      		<small class="text-muted">1 hour ago</small>
		    	</div>
		    	<p class="mb-1">I'm funny, right? What do you know, you're a door. You only like knock-knock jokes.</p>
		    	<div class="d-flex w-100 justify-content-between">
		    		<button type="button" class="btn btn-link btn-sm">delete</button>
		    		<small class="text-muted">September 4, 2018</small>
		    	</div>
		    	<div class="pull-right">
		    		<p class="text-right">
		    			<small class="text-muted">Bill Clinton</small>
		    			I'm funny, right? What do you know, you're a door. You only like knock-knock jokes.
		    			<small class="text-muted">9/12/2018 5:02pm</small>
		    			<button type="button" class="btn btn-link btn-sm">delete</button>
		    		</p>
		    		<form class="form-inline">
		    			<div class="form-group">
		    				<input type="text" class="form-control" id="InputEmail" placeholder="comment">
		    			</div>
						<button type="submit" class="btn btn-success">Comment</button>
					</form>	
		    	</div>
	  		</div>
	  		<div class="list-group-item list-group-item-action flex-column">
		    	<div class="d-flex w-100 justify-content-between">
		    		<small class="text-muted">09:30 am</small>
		      		<small class="text-muted">1 hour ago</small>
		    	</div>
		    	<p class="mb-1">I wanted in line for 15 minutes to get my lunch.</p>
		    	<div class="d-flex w-100 justify-content-between">
		    		<button type="button" class="btn btn-link btn-sm">delete</button>
		    		<small class="text-muted">September 4, 2018</small>
		    	</div>
		    	<div class="pull-right">
		    		<p class="text-right">
		    			<small class="text-muted">Barack Obama</small>
		    			Just come to my place. I am available.
		    			<small class="text-muted">9/12/2018 5:02pm</small>
		    			<button type="button" class="btn btn-link btn-sm">delete</button>
		    		</p>
		    		<form class="form-inline">
		    			<div class="form-group">
		    				<input type="text" class="form-control" id="InputComment" placeholder="comment">
		    			</div>
						<button type="submit" class="btn btn-success">Comment</button>
					</form>		
		    	</div>
	  		</div>
		</div>
	</div>


</body>
</html>