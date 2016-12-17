<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
       
<!DOCTYPE html>
<html ng-app="app">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/textAngular/1.4.3/dist/textAngular.css">
 <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,300">


</head>
<body>

</head>
<body>
<nav class="navbar navbar-custom navbar-static-top">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Brand</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="false" aria-expanded="true">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
      </ul>
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Link</a></li>
         <li><a href="#">Link</a></li>
          <li><a href="#">Link</a></li>
           <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->

</nav>
<%-- <h1>HELLO WORLD   </h1>
<p ng-if="${pageContext.request.userPrincipal.name != null}">
Welcome:${pageContext.request.userPrincipal.name}
<a href="perform_logout">Logout</a>
</p>

<p ng-if="${pageContext.request.userPrincipal.name == null}">

<a href="#/loginpage">Login</a>
</p> --%>


 <%-- <c:if test="${pageContext.request.userPrincipal.name != null}">
Welcome:${pageContext.request.userPrincipal.name}
<a href="perform_logout">logout</a></c:if>
<c:if test="${pageContext.request.userPrincipal.name == null}">
<a href="#/loginpage">Login</a>
</c:if> --%>
<div class="container" ng-view></div>



<script src="resources/js/external/jquery.min.js"></script>

	<script src="resources/js/bootstrap.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.9/angular.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.9/angular-resource.min.js"></script>	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.9/angular-route.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.9/angular-cookies.js"></script>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/textAngular/1.4.3/dist/textAngular.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/textAngular/1.4.3/dist/textAngular-sanitize.min.js"></script>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/textAngular/1.4.3/dist/textAngular-rangy.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/danialfarid-angular-file-upload/12.2.13/ng-file-upload-all.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/danialfarid-angular-file-upload/12.2.13/ng-file-upload-shim.js"></script>
	<!-- <script src="resources/js/external/angular.js"></script> 
	
	
    <script src="resources/js/external/angular-resource.min.js"></script>  
    <script src="resources/js/external/angular-route.js"></script> 
    <script src="resources/js/external/angular-cookies.js"></script> 
    <script src="resources/js/external/textAngular.min.js"></script>  -->
   
   <!--  <script src="resources/js/sockjs.js"></script>  -->
    <script src="resources/js/external/sockjs.min.js"></script> 
    <script src="resources/js/external/stomp.js"></script> 
    <script src="resources/js/external/stomp.min.js"></script> 
    <!-- <script src="resources/lodash/dist/external/lodash.min.js"></script> -->

	<script src="resources/js/app.js"></script>
<script src="resources/js/BlogController.js"></script>
<script src="resources/js/ViewBlogController.js"></script>
<script src="resources/js/BlogCommentController.js"></script>
<script src="resources/js/UserController.js"></script>
<script src="resources/js/UserService.js"></script>
<script src="resources/js/ForumController.js"></script>    

<!-- <script src="resources/js/jquery-3.1.0.min.js"></script>
<script src="resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>


<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-resource/1.5.9/angular-resource.min.js"></script>
<script src="resources/js/angular-route.js"></script>
<script src="resources/js/angular-cookies.js"></script>

<script src="resources/js/app.js"></script>
<script src="resources/js/BlogController.js"></script>
<script src="resources/js/UserController.js"></script> -->




</body>
</html>