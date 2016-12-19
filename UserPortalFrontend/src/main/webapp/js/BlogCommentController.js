app.factory('BlogComment', ['$resource', function ($resource) {
	return $resource('http://localhost:8080/UserPortal/blogComment:blogId/:personId', {blogId: '@blogId', personId: '@blogCommentId'},
	{
		updateBlogComment: {method: 'PUT'}
	}
    );s
}]);
app.controller('BlogCommentController', ['$scope','$location' ,'BlogComment', function($scope, $location , BlogComment) {
    var ob = this;
    ob.blogComments=[];
    ob.blogComment = new BlogComment(); 
    ob.fetchAllBlogComments = function(id){
    	
        ob.blogComments = BlogComment.query({ blogId: id});
    };
    ob.fetchAllBlogComments();
    
    ob.addBlogComment = function(){
	console.log('Inside save');
	if($scope.blogCommentForm.$valid) {
	  ob.blogComment.$save(function(blogComment){
	     console.log(blogComment); 
	     ob.flag= 'created';	
	     ob.reset();	
	     ob.fetchAllBlogComments();
	  },
	  function(err){
	     console.log(err.status);
	     ob.flag='failed';
	  }
          );
        }
    }; 
    ob.editBlogComment = function(id){
	    console.log('Inside edit');
            ob.blogComment = BlogComment.get({ personId: id}, function() {
	       ob.flag = 'edit'; 
	    });
    };    
    ob.updateBlogDetail = function(){
	console.log('Inside update');
	if($scope.blogCommentForm.$valid) {
    	   ob.blogComment.$updateBlogComment(function(blogComment){
    		console.log(blogComment); 
		ob.updatedId = blogComment.blogCommentId;
		ob.reset();
		ob.flag = 'updated'; 
    		ob.fetchAllBlogComments();
           });
	}
    };	
    ob.deleteBlogComment = function(id){
	    console.log('Inside delete');
	    ob.blogComment = BlogComment.delete({ personId: id}, function() {
		ob.reset();  
		ob.flag = 'deleted';
    		ob.fetchAllBlogComments(); 
	    });
    };		
    ob.reset = function(){
    	ob.blogComment = new BlogComment();
        $scope.blogCommentForm.$setPristine();
        
    };	
    ob.cancelUpdate = function(id){
	    ob.blogComment = new BlogComment();
	    ob.flag= '';	
   	    ob.fetchAllBlogComments();
    };
    ob.viewBlogComment = function(id){
	    console.log('Inside view');
            ob.blogview = BlogComment.get({ personId: id}, function() {
	       ob.flag = 'view'; 
	    });
    }; 
    $scope.clearFilter = function() {
        
        location.reload();
        location.reload();


      };
}]);     