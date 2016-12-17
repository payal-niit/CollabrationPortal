<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div ng-controller="BlogController as blogCtrl">
	<div class="table-responsive">
	<h2>Blog List</h2>
        <table class="table table-hover table-bordered">
	      <tr>
	      <td>Sr No</td>
	      <td>Blog Title</td>	      
	      <td>Created By</td>
	      <td>Operations</td>
	      </tr>
	      <tr ng-repeat="row in blogCtrl.blogs">
	         <td><span ng-bind="row.blogId"></span></td>
	         <td><span ng-bind="row.blogName"></span></td>
	         <td><span ng-bind="row.user.username"></span></td>
	         
	         <td>
	         <!-- <div ng-controller="ViewBlogController as view">
	          <input class="btn btn-info" type="button" ng-click="view.viewblog(row.blogId)" value="View"/>
		    </div> -->
		    <input class="btn btn-danger" type="button" ng-click="blogCtrl.deleteBlog(row.blogId)" value="Delete"/>
		    <input class="btn btn-success" type="button" ng-click="blogCtrl.editBlog(row.blogId)" value="Edit"/>
		    <input class="btn btn-info" type="button" ng-click="blogCtrl.viewBlog(row.blogId)" value="View" data-toggle="modal" data-target="#myModal" />
		    <span ng-if="blogCtrl.flag=='updated' && row.blogId==blogCtrl.updatedId" class="msg-success">Blog successfully updated.</span> </td> 
	      </tr>	
	</table>
	</div>
	
	<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
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
      <!-- <input type="text" value="{{blogCtrl.blogview.blogId}}"  ng-model="blogCommentCtrl.blogComment.blogId"/> -->
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


	
</body>
</html>