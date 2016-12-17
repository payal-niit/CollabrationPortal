<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Login


<div class="container">
<br>
	<div class="container">
	<div class="col-md-6 col-xs-8 col-lg-8 col-sm-8" style="margin-left: 200px; text-align:center;border: 1px solid grey;">
		<h3>Login in</h3>
		<hr class="colorgraph">
 <form method="POST" action="perform_login" class="form-signin">
 			<div class="row">
		<div class="col-md-4 col-xs-4 col-lg-4 col-sm-4">

            
            <input name="username" type="text" class="form-control" placeholder="Username"
                   autofocus />
                   </div>
                   <div class="col-md-4 col-xs-4 col-lg-4 col-sm-4">
            <input name="password" type="password" class="form-control" placeholder="Password"/>
            </div>
           
            <div class="col-md-2">
            <button class="btn btn btn-primary" type="submit">Log In</button>
            </div></div></form>
            <hr>
            <h4 class="text-center"><a href="register">Create an account</a></h4>
        </div>
        
        </div>
    

</div>
</body>
</html>