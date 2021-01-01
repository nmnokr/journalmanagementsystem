<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>REGISTRATION</title>
		<link rel="stylesheet" href="css/bootstrap.css"><!-- importing bootstrap file -->
	</head>
	<body>
		<div class="container">
		<h1>Jquery Validation For Jsp with Bootstrap Design</h1>
		<form action ="view.jsp" method="post" role="form" class="form-horizontal col-lg-8 col-md-8 col-sm-8 col-xs-12" autocomplete="off">
			<div class="form-group">
				<label for="id">USER ID</label>
				<input type="text" class="form-control" id="id"  name="userid" placeholder="Enter ID">
				<span id="errid" class="alert alert-danger col-lg-8 col-md-8 col-sm-8 col-xs-12">Should Contain Only Digits</span>
			</div>
			<div class="form-group">
				<label for="name">USER NAME</label>
				<input type="text" class="form-control" id="username"  name="username" placeholder="Enter Name">
				<span id="errname" class="alert alert-danger col-lg-8 col-md-8 col-sm-8 col-xs-12">Should Contain Only Characters</span>
			</div>
			<div class="form-group">
				<label for="password">PASSWORD</label>
				<input type="password" class="form-control" id="password"  name="password" placeholder="Enter Password">
			</div>
			<div class="form-group">
				<label for="cpassword">Confirm PASSWORD</label>
				<input type="password" class="form-control" id="cpassword"  name="password" placeholder="Enter Password">
				<span id="errpass" class="alert alert-danger col-lg-8 col-md-8 col-sm-8 col-xs-12">Should Match the passwords</span>
			</div>
			<div class="form-group">
				<label for="email">EMAIL</label>
				<input type="email" class="form-control" id="email"  name="email" placeholder="Enter email">
				<span id="erremail" class="alert alert-danger col-lg-8 col-md-8 col-sm-8 col-xs-12">Invalid Email Address</span>
			</div>
			<div class="form-group">
				<button class="btn btn-success" type="submit" name="btn" id="Rbtn" value="register">REGISTER</button>
			</div>
		</form>
		</div>

		<script src="js/jquery-3.1.1.min.js"></script>  
		<!-- here we are importing the jquery file which is downloaded -->
		<script src="js/validate.js"></script>
	</body>
</html>