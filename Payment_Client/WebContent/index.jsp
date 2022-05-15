<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	if (session.getAttribute("Username") != null) {
	response.sendRedirect("Payment.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="Views/index.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/auth.js"></script>

</head>
<body>
	
		<div class="row">
			<div class="col-lg-5 m-auto">
			<div class="card mt-5 bg-dark">
			<div class="card-title text-center mt-3" >
			     <img src="Media/clipart166560.png" width="150px" height="150px">
				<h1 class="h1edit">LOGIN</h1>
				<form id="formLogin">
				<div class="input-group mb-3">
				<div class="input-group-prepend">
				<span class="input-group-text">
				<i class="fa fa-user fa-2x"></i>
				</span>
				</div>
					<input id="txtUsername" name="txtUsername" type="text" placeholder="User Name"
						class="form-control form-control-sm"> 
				</div>		
				
				<div class="input-group mb-3">
				<div class="input-group-prepend">
				<span class="input-group-text">
				<i class="fa fa-lock fa-2x"></i>
				</span>
				</div>
						<input id="txtPassword" name="txtPassword" type="password" placeholder="Password"
						class="form-control form-control-sm"> 
						
				</div>		
						
						<br> 	
						<input id="btnLogin" name="btnLogin" type="button" value="Login"
						class="btn btn-primary"> <br> <br>
					<div id="alertError" class="alert alert-danger"></div>
					
					
				</form>
				</div>
			</div>
		</div>
		</div>
</body>
</html>