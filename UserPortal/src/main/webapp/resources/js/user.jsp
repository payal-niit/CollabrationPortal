<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Add User

<div class="container" ng-controller="UserController as userCtrl">
       <h1> Spring MVC 4 REST + AngularJS </h1>
	<form role="form" name="userForm" method="POST">
	    <table class="table table-bordered">
		<tr><td colspan="2">
		  <div ng-if="userCtrl.flag != 'edit'">
		     <h3> Add New User </h3> 
		  </div>
		  <div ng-if="userCtrl.flag == 'edit'">
		     <h3> Update User for ID: {{ userCtrl.user.userId }} </h3> 
		  </div> </td>
		</tr>
		
		<tr>
		      <td>Name: </td> <td><input class="form-control" type="text" name="name" ng-model="userCtrl.user.username" required/> 
         	      <span ng-show="userForm.username.$error.required" class="msg-val">Name is required.</span> </td>
		</tr>
		<tr>
		      <td>Password: </td> <td><input class="form-control" type="text" name="name" ng-model="userCtrl.user.password" required/> 
         	      <span ng-show="userForm.password.$error.required" class="msg-val">Password is required.</span> </td>
		</tr>
		<tr>
		      <td>FirstName: </td> <td> <input class="form-control" type="text" name="fname" ng-model="userCtrl.user.fname" required/> 
	              <span ng-show="userForm.fname.$error.required" class="msg-val">Location is required.</span> </td>
	              <td>LastName: </td> <td> <input class="form-control" type="text" name="lname" ng-model="userCtrl.user.lname" required/> 
	              <span ng-show="userForm.lname.$error.required" class="msg-val">Location is required.</span> </td>
		</tr>
		
		<tr>
		      <td>Gender: </td> <td> <input type="radio" name="gender" value="Male" ng-model="userCtrl.user.gender" />Male 
		      <input type="radio" name="gender" value="Female" ng-model="userCtrl.user.gender" /> Female
	              <span ng-show="userForm.gender.$error.required" class="msg-val">Location is required.</span> </td>
		</tr>
		<tr>
		      <td>Age: </td> <td><input class="form-control" type="text" name="age" ng-model="userCtrl.user.age" required/> 
         	      <span ng-show="userForm.age.$error.required" class="msg-val">Password is required.</span> </td>
		</tr>
		<tr>
		     <td colspan="2"> <span ng-if="userCtrl.flag=='created'" class="msg-success">User successfully added.</span>
		     <span ng-if="userCtrl.flag=='failed'" class="msg-val">User already exists.</span> </td>
		</tr>
	        <tr><td colspan="2">
	            <div ng-if="userCtrl.flag != 'edit'">
		       <input  type="submit" ng-click="userCtrl.addUser()" value="Add User"/> 
		       <input type="button" ng-click="userCtrl.reset()" value="Reset"/>
		    </div>
		    <div ng-if="userCtrl.flag == 'edit'">
		       <input  type="submit" ng-click="userCtrl.updateUserDetail()" value="Update User"/> 	
			   <input type="button" ng-click="userCtrl.cancelUpdate()" value="Cancel"/>				   
		    </div> </td>
		</tr>
		<tr>
		     <td colspan="2"> <span ng-if="userCtrl.flag=='deleted'" class="msg-success">User successfully deleted.</span>
		</tr>
	    </table>     
	</form>
	<div class="table-responsive">
	<h2>User List</h2>
        <table class="table table-hover table-bordered">
	      <tr><th>ID </th> <th>Name</th> <th>Description</th><th>UserId</th></tr>
	      <tr ng-repeat="row in userCtrl.users">
	         <td><span ng-bind="row.userId"></span></td>
	         <td><span ng-bind="row.username"></span></td>
	         <td><span ng-bind="row.gender"></span></td>
	         <td><span ng-bind="row.role"></span></td>
	         <td><span ng-bind="row.fname"></span></td>
	         <td><span ng-bind="row.lname"></span></td>
	         <td>
		    <input class="btn btn-info" type="button" ng-click="userCtrl.deleteUser(row.userId)" value="Delete"/>
		    <input class="btn btn-info" type="button" ng-click="userCtrl.editUser(row.userId)" value="Edit"/>
		    <span ng-if="userCtrl.flag=='updated' && row.userId==userCtrl.updatedId" class="msg-success">User successfully updated.</span> </td> 
	      </tr>	
	</table>
	</div>
</div>
        
	
</body>
</html>