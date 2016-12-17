<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body ng-controller="ViewBlogController as view">
hi
{{blog.blogId}}
<p ng-model="view.blog.blogName">

{{currentBlog.blogId}}
<h2>
{{currentBlog.blogName}}
</h2>
{{currentBlog.blogDescription}}
{{currentBlog.userId}}

<i>{{currentBlog}}</i>
<script type="text/javascript">
localStorage.setItem("currentBlog", angular.toJson(currentBlog));
var currentBlog = localStorage.getItem("currentBlog");
</script> 
</body>
</html>