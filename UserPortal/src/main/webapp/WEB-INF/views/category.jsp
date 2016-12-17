<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="category" action="addcategory">
		<form:input path="categoryName" placeholder="Add categoryName" />
		<form:input path="categoryDesc" placeholder="Add categoryDescription" />
		<input type="submit" value="Add category">
	</form:form>
	<hr>
	<h2>Category List</h2>
	<table>
	<tr>
	<th>CategoryID</th>
	<th>Category Name</th>
	<th>Category Desc</th>	
	<th>Edit</th>
	</tr>
	
	<c:forEach items="${categoryList}" var="c">
	<tr>
	<td>${c.categoryId}</td>
	<td>${c.categoryName}</td>
	<td>${c.categoryDesc}</td>	
	<td><a href="edit-${c.categoryId}">Edit</a></td>
	<td><a href="deleteCategory-${c.categoryId}">Delete</a></td>
	
	</tr>
	</c:forEach>
	
	
	</table>
</body>
</html>