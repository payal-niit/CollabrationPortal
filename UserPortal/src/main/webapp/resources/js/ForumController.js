app.factory('Forum', ['$resource', function ($resource) {
	return $resource('http://localhost:8080/UserPortal/forum/:personId','fd', {personId: '@forumId'},
	{
		updateForum: {method: 'PUT'},
		save: {
			 method: "POST",
			 
	            transformRequest: formDataObject,
	            headers : {
	            	'Content-Type' : 'multipart/form-data' ,'enctype':'multipart/form-data'
	            	}
	           
		}
		
	}
	
    );
	
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
function formDataObject (data) {
    var fd = new FormData();
    angular.forEach(data, function(value, key) {
        fd.append(key, value);
    });
    return fd;
}
app.controller('ForumController', ['$scope', 'Forum', function($scope, Forum) {
    var ob = this;
    ob.forums=[];
    ob.forum = new Forum(); 
    ob.fetchAllForums = function(){
        ob.forums = Forum.query();
    };
    ob.fetchAllForums();
    
    ob.addForum = function(){
	console.log('Inside save');
	if($scope.forumForm.$valid) {
	  ob.forum.$save(function(forum){
		  var productImage = $scope.myFile;
		  console.log('file is ' );
		  console.dir(productImage);
		  var fd = new FormData();
		  fd.append('file', productImage);
		  fd.append('forum',angular.toJson($scope.forum,true));
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
    }; 
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