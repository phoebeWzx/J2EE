<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>HomePage</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/Layout.css">
</head>
<body>
	<jsp:include page="TopNav_Template_L.html" />
	<div class="divBox">
		<jsp:include page="SideNav_Template_L.html" />
		<div class="right">
			<br><h2>Georage Bush's Blog</h2><br>
	  		<div class="list-group-item list-group-item-action flex-column">
		    	<div class="d-flex w-100 justify-content-between">
		    		<small class="text-muted">09:30 am</small>
		      		<small class="text-muted">1 hour ago</small>
		    	</div>
		    	<p class="mb-1">I will go to Wean Hall Library. Is there someone over there?</p>
		    	<div class="d-flex w-100 justify-content-between">
		    		<button type="button" class="btn btn-link btn-sm">delete</button>
		    		<small class="text-muted">September 4, 2018</small>
		    	</div>
		    	<div class="pull-right">
		    		<p class="text-right">
		    			<small class="text-muted">Bill Clinton</small>
		    			I'm here! See you later!
		    			<small class="text-muted">9/12/2018 5:02pm</small>
		    			<button type="button" class="btn btn-link btn-sm">delete</button>
		    		</p>	
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
		    	</div>
	  		</div>

			<div class="form-group">
    			<textarea class="form-control" id="Textarea1" rows="3" maxlength="42" placeholder="No more than 42 characters"></textarea>
    			<br>
    			<button class="btn btn-outline-success" type="submit">Post a new blog!</button>
  			</div>
		</div>
	</div>


</body>
</html>