app.factory('Forum', ['$resource', function ($resource) {
	return $resource('http://localhost:8080/UserPortal/forum/:personId','fd', {personId: '@forumId'},
	{
		updateForum: {method: 'PUT'},
		save: {
			 method: "POST",
			 
	            transformRequest: angular.identity,
	            headers : {
	            	'Content-Type' : undefined
	            	}           
		}		
	});
	
}]);
app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

app.service('ForumAdd', ['$http','$location', function ($http,$scope,$location) {
    this.uploadFileToUrl = function(productImage, uploadUrl,forumTitle,forumDescription){
       var fd = new FormData();
       fd.append('productImage', productImage);
       fd.append('forumTitle',forumTitle);
       fd.append('forumDescription',forumDescription);
       
    console.log("fd:"+fd)
       $http.post(uploadUrl, fd, {
          transformRequest: angular.identity,
          headers: {'Content-Type': undefined}
       })
    
       .success(function(){
    	   $scope.message="registered! you can login now!!";
    	    $scope.forumTitle="";
    	    $scope.forumDewscription="";
    	    location.reload();
    	   
       })
    
       .error(function(){
       });
    }
 }]);

/*app.controller('ForumController', ['$scope', 'Forum', function($scope,Forum){
    $scope.register = function(){
       var productImage = $scope.myFile;
       var forumTitle=$scope.forumTitle;
       var forumDescription=$scope.forumDescription;
      
       console.log("username"+forumTitle);
       console.log('file is ' );
       console.dir(productImage);
       var uploadUrl = "http://localhost:8080/UserPortal/forum";
       Forum.uploadFileToUrl(productImage, uploadUrl,forumDescription);
    };
 }]);*/

app.controller('ForumController', ['$scope', 'Forum', 'ForumAdd',function($scope, Forum,ForumAdd) {
    var ob = this;
    ob.forums=[];
    ob.forum = new Forum(); 
    ob.fetchAllForums = function(){
        ob.forums = Forum.query();
    };
    ob.fetchAllForums();
    
    ob.addForum = function(){
    	if($scope.forumForm.$valid) {
        var productImage = $scope.myFile;
        var forumTitle=$scope.forumTitle;
        var forumDescription=$scope.forumDescription;
       
        console.log("username"+forumTitle);
        console.log('file is ' );
        console.dir(productImage);
        ob.flag= 'created';	
	     ob.reset();	
	     ob.fetchAllForums();
        var uploadUrl = "http://localhost:8080/UserPortal/forum";
        ForumAdd.uploadFileToUrl(productImage, uploadUrl,forumTitle,forumDescription);
        ob.flag= 'created';	
	     ob.reset();	
	     ob.fetchAllForums();
	     
	     console.log('You reached here ' );
	  }},
	  function(err){
	     console.log(err.status);
	     ob.flag='failed';
	  
    };

    
    
    /*ob.addForum = function(){
	console.log('Inside save');
	if($scope.forumForm.$valid) {
	  ob.forum.$save(function(forum){
		  var productImage = $scope.myFile;
		  console.log('file is ' );
		  console.dir(productImage);
		  
		  
		  var forumTitle=$scope.forumTitle;
		  var forumDescription=$scope.forumDescription;
		  console.log('forum title is '+ forumTitle + forumDescription);
		  
		  var uploadUrl = "http://localhost:8080/UserPortal/forum";
		  Forum.uploadFileToUrl(productImage, uploadUrl,forumTitle,forumDescription);
	       
	     console.log(forum); 
	     ob.flag= 'created';	
	     ob.reset();	
	     ob.fetchAllForums();
	  },
	  function(err){
	     console.log(err.status);
	     ob.flag='failed';
	  }
          );
        }
    }; */
    ob.editForum = function(id){
	    console.log('Inside edit');
            ob.forum = Forum.get({ personId: id}, function() {
	       ob.flag = 'edit'; 
	    });
    };    
    ob.updateForumDetail = function(){
	console.log('Inside update');
	if($scope.forumForm.$valid) {
    	   ob.forum.$updateForum(function(forum){
    		console.log(forum); 
		ob.updatedId = forum.forumId;
		ob.reset();
		ob.flag = 'updated'; 
    		ob.fetchAllForums();
           });
	}
    };	
    ob.deleteForum = function(id){
	    console.log('Inside delete');
	    ob.forum = Forum.delete({ personId: id}, function() {
		ob.reset();  
		ob.flag = 'deleted';
    		ob.fetchAllForums(); 
	    });
    };		
    ob.reset = function(){
    	ob.forum = new Forum();
        $scope.forumForm.$setPristine();
    };	
    ob.cancelUpdate = function(id){
	    ob.forum = new Forum();
	    ob.flag= '';	
   	    ob.fetchAllForums();
    };
    ob.viewForum = function(id){
	    console.log('Inside view');
            ob.forumview = Forum.get({ personId: id}, function() {
	       ob.flag = 'view'; 
	    });
    };
    
}]);     