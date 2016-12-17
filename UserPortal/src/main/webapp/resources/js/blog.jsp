<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<div class="container" ng-controller="BlogController as blogCtrl">
       
	<form role="form" name="blogForm" method="POST">
	    <table class="table table-bordered">
		<tr><td colspan="2">
		  <div ng-if="blogCtrl.flag != 'edit'">
		     <h3 > Add New Blog </h3> <small style="float:right;"><a href="#/bloglist">BlogList</a></small>
		  </div>
		  <div ng-if="blogCtrl.flag == 'edit'">
		     <h3> Update Blog for ID: {{ blogCtrl.blog.blogId }} </h3> 
		  </div> </td>
		</tr>
		
		<tr>
		      <td colspan="2"><input class="form-control" type="text" placeholder="Title" name="name" ng-model="blogCtrl.blog.blogName" required/> 
         	      <span ng-show="blogForm.blogName.$error.required" class="msg-val">Name is required.</span> </td>
		</tr>
		<tr>
		       <td> <input class="form-control" type="hidden" name="blogDescription" ng-model="blogCtrl.blog.blogDescription" required/> </td>
	              
		</tr>
		<tr>
		<td colspan="2">
		<div text-angular ng-model="blogCtrl.blog.blogDescription" name="demo-editor" ta-text-editor-class="border-around" ta-html-editor-class="border-around" placeholder="Write here..."></div>
	</td>
		</tr>
		<tr>
		      <!-- <td>UserID: </td> <td> <input class="form-control" type="text" name="userId" ng-model="blogCtrl.blog.userId" required/> 
	              <span ng-show="blogForm.userId.$error.required" class="msg-val">Location is required.</span> </td> -->
			
		
		</tr>
		
		<tr>
		     <td colspan="2"> <span ng-if="blogCtrl.flag=='created'" class="msg-success">Blog successfully added. <a href="#/bloglist" class="btn btn-info">All Blogs <span class="glyphicon glyphicon-th-list"></span></a></span>
		     <span ng-if="blogCtrl.flag=='failed'" class="msg-val">Blog already exists.</span> </td>
		</tr>
	        <tr><td colspan="2">
	            <div ng-if="blogCtrl.flag != 'edit'">
		       <input  class="btn btn-primary" type="submit" ng-click="blogCtrl.addBlog()" value="Add Blog"/> 
		       <input class="btn btn-default" type="button" ng-click="blogCtrl.reset()" value="Reset"/>
		    </div>
		    <div ng-if="blogCtrl.flag == 'edit'">
		       <input  type="submit" ng-click="blogCtrl.updateBlogDetail()" value="Update Blog"/> 	
			   <input type="button" ng-click="blogCtrl.cancelUpdate()" value="Cancel"/>				   
		    </div> </td>
		</tr>
		<tr>
		     <td colspan="2"> <span ng-if="blogCtrl.flag=='deleted'" class="msg-success">Blog successfully deleted.</span>
		</tr>
	    </table>     
	</form>
	<div class="table-responsive">
	<h2>User List</h2>
        <table class="table table-hover table-bordered">
	      <tr><th>ID </th> <th>Name</th> <th>Description</th><th>UserId</th></tr>
	      <tr ng-repeat="row in blogCtrl.blogs">
	         <td><span ng-bind="row.blogId"></span></td>
	         <td><span ng-bind="row.blogName"></span></td>
	         <td><span ng-bind-html="row.blogDescription"></span></td>
	         <td><span ng-bind="row.userId"></span></td>
	         <td>
	         <div ng-controller="ViewBlogController as view">
	          <input class="btn btn-info" type="button" ng-click="view.viewblog(row.blogId)" value="View"/>
		    </div>
		    <input class="btn btn-info" type="button" ng-click="blogCtrl.deleteBlog(row.blogId)" value="Delete"/>
		    <input class="btn btn-info" type="button" ng-click="blogCtrl.editBlog(row.blogId)" value="Edit"/>
		    <input class="btn btn-info" type="button" ng-click="blogCtrl.viewBlog(row.blogId)" value="View" data-toggle="modal" data-target="#myModal" />
		    <span ng-if="blogCtrl.flag=='updated' && row.blogId==blogCtrl.updatedId" class="msg-success">Blog successfully updated.</span> </td> 
	      </tr>	
	</table>
	</div>
	
	Modal
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    Modal content
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Blog name: {{blogCtrl.blogview.blogName}}</h4>
        
      </div>
      <div class="modal-body">
        <p ng-bind-html="blogCtrl.blogview.blogDescription"></p>
      </div>
      <div class="modal-footer">
      <div ng-controller="BlogCommentController as blogCommentCtrl">
      <form role="form" name="blogCommentForm" method="POST">
      <label>Add comment</label>
      <input type="text" ng-model="blogCommentCtrl.blogComment.commentDetail" required/>
      <div ng-if="blogCtrl.flag != 'edit'">
      <input  type="submit" ng-click="blogCommentCtrl.addBlogComment()" value="Add Comment"/> 
      </div>
      </form>
      
      <table class="table table-hover table-bordered">
	     <tr><th>Comments</th><th>Operations</th></tr>
	      <tr ng-repeat="row in blogCommentCtrl.blogComments">
	      <td>{{row.commentDetail}} {{row.dateOfComment}}</td>
	      <td>  <input class="btn btn-info" type="button" ng-click="blogCommentCtrl.deleteBlogComment(row.blogCommentId)" value="Delete"/></td>
      </tr>
      </table>
      </div>
        <a href="#" ng-click="clearFilter()" class="btn btn-default" data-dismiss="modal">Close</a>
      </div>
    </div>

  </div>
</div>

</div>
<script type="text/javascript">

localStorage.setItem("currentBlog", angular.toJson(currentBlog));
var currentBlog = localStorage.getItem("currentBlog");
</script> 

	 
	 
</body>
</html>